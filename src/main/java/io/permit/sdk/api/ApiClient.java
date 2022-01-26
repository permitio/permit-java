package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.models.*;
import io.permit.sdk.enforcement.User;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

interface IReadApis {
    UserModel getUser(String userKey) throws IOException;
    RoleModel getRole(String roleKey) throws IOException;
    TenantModel getTenant(String tenantKey) throws IOException;
    RoleAssignmentList getAssignedRoles(String userKey, String tenantKey) throws IOException;
    RoleAssignmentList getAssignedRolesInAllTenants(String userKey) throws IOException;
}

interface IWriteApis {
    UserModel syncUser(User user) throws IOException;
    Boolean deleteUser(String userKey) throws IOException;
    TenantModel createTenant(TenantInput tenant) throws IOException;
    TenantModel updateTenant(TenantInput tenant) throws IOException;
    Boolean deleteTenant(String tenantKey) throws IOException;
    RoleAssignmentModel assignRole(String userKey, String roleKey, String tenantKey) throws IOException;
    Boolean unassignRole(String userKey, String roleKey, String tenantKey) throws IOException;
    ResourceList syncResources(SyncedResources spec) throws IOException;
}

public class ApiClient implements IReadApis, IWriteApis {
    final static Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private final OkHttpClient client = new OkHttpClient();
    private final PermitConfig config;
    private final Headers headers;
    private final String baseUrl;

    public ApiClient(PermitConfig config) {
        this.config = config;
        this.headers = new Headers.Builder()
            .add("Content-Type", "application/json")
            .add("Authorization", String.format("Bearer %s", this.config.getToken()))
            .build();
        this.baseUrl = this.config.getPdpAddress();
    }

    private void logResponse(String requestRepr, Response response, String responseContent) {
        String log = String.format("Received response: %s : status code %d : %s", requestRepr, response.code(), responseContent);
        if (!response.isSuccessful() && this.config.isDebugMode()) {
            this.logger.error(log);
        } else {
            this.logger.debug(log);
        }
    }

    @Override
    public UserModel getUser(String userKey) throws IOException {
        String url = String.format("%s/cloud/users/%s", this.baseUrl, userKey);
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .get()
                .build();

        String requestRepr = String.format("permit.api.getUser(%s)", userKey);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            Gson gson = new Gson();
            return gson.fromJson(responseString, UserModel.class);
        }
    }

    @Override
    public RoleModel getRole(String roleKey) throws IOException {
        String url = String.format("%s/cloud/roles/%s", this.baseUrl, roleKey);
        Request request = new Request.Builder()
            .url(url)
            .headers(this.headers)
            .get()
            .build();

        String requestRepr = String.format("permit.api.getRole(%s)", roleKey);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            Gson gson = new Gson();
            return gson.fromJson(responseString, RoleModel.class);
        }
    }

    @Override
    public TenantModel getTenant(String tenantKey) throws IOException {
        String url = String.format("%s/cloud/tenants/%s", this.baseUrl, tenantKey);
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .get()
                .build();

        String requestRepr = String.format("permit.api.getTenant(%s)", tenantKey);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            Gson gson = new Gson();
            return gson.fromJson(responseString, TenantModel.class);
        }
    }

    @Override
    public RoleAssignmentList getAssignedRoles(@NotNull String userKey, String tenantKey) throws IOException {
        String url = String.format("%s/role_assignments?user=%s", this.baseUrl, userKey);
        if (tenantKey != null) {
            url = url + String.format("&tenant=%s", tenantKey);
        }
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .get()
                .build();

        String requestRepr = String.format("permit.api.getAssignedRoles(user=%s, tenant=%s", userKey, tenantKey);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            Gson gson = new Gson();
            return gson.fromJson(responseString, RoleAssignmentList.class);
        }
    }

    @Override
    public RoleAssignmentList getAssignedRolesInAllTenants(String userKey) throws IOException {
        return this.getAssignedRoles(userKey, null);
    }

    @Override
    public UserModel syncUser(User user) throws IOException {
        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(user);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/cloud/users", this.baseUrl);
        Request request = new Request.Builder()
            .url(url)
            .headers(this.headers)
            .put(body)
            .build();

        String requestRepr = String.format("permit.api.syncUser(%s)", requestBody);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            return gson.fromJson(responseString, UserModel.class);
        }
    }

    @Override
    public Boolean deleteUser(String userKey) throws IOException {
        // create the request
        String url = String.format("%s/cloud/users/%s", this.baseUrl, userKey);
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .delete()
                .build();

        String requestRepr = String.format("permit.api.deleteUser(%s)", userKey);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful(); // return 204 on success, error codes otherwise
        }
    }

    @Override
    public TenantModel createTenant(TenantInput tenant) throws IOException {
        NewTenant newTenant = new NewTenant();
        newTenant.externalId = tenant.key;
        newTenant.name = tenant.name;
        if (tenant.description != null) {
            newTenant.description = tenant.description;
        }
        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(newTenant);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/cloud/tenants", this.baseUrl);
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .put(body)
                .build();

        String requestRepr = String.format("permit.api.createTenant(%s)", requestBody);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            return gson.fromJson(responseString, TenantModel.class);
        }
    }

    @Override
    public TenantModel updateTenant(TenantInput tenant) throws IOException {
        NewTenant newTenant = new NewTenant();
        newTenant.name = tenant.name;
        if (tenant.description != null) {
            newTenant.description = tenant.description;
        }
        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(newTenant);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/cloud/tenants/%s", this.baseUrl, tenant.key);
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .patch(body)
                .build();

        String requestRepr = String.format("permit.api.updateTenant(%s)", requestBody);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            return gson.fromJson(responseString, TenantModel.class);
        }
    }

    @Override
    public Boolean deleteTenant(String tenantKey) throws IOException {
        // create the request
        String url = String.format("%s/cloud/tenants/%s", this.baseUrl, tenantKey);
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .delete()
                .build();

        String requestRepr = String.format("permit.api.deleteTenant(%s)", tenantKey);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful(); // return 204 on success, error codes otherwise
        }
    }

    @Override
    public RoleAssignmentModel assignRole(String userKey, String roleKey, String tenantKey) throws IOException {
        RoleAssignmentInput input = new RoleAssignmentInput();
        input.user = userKey;
        input.role = roleKey;
        input.scope = tenantKey;

        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(input);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/cloud/role_assignments", this.baseUrl);
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .post(body)
                .build();

        String requestRepr = String.format("permit.api.assignRole(%s)", requestBody);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            return gson.fromJson(responseString, RoleAssignmentModel.class);
        }
    }

    @Override
    public Boolean unassignRole(String userKey, String roleKey, String tenantKey) throws IOException {
        // create the request
        String url = String.format(
                "%s/cloud/role_assignments?role=%s&user=%s&scope=%s",
                this.baseUrl,
                roleKey,
                userKey,
                tenantKey
            );
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .delete()
                .build();

        String requestRepr = String.format("permit.api.unassignRole(%s,%s,%s)", userKey, roleKey, tenantKey);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful(); // return 204 on success, error codes otherwise
        }
    }

    @Override
    public ResourceList syncResources(SyncedResources spec) throws IOException {
        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(spec);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/cloud/resources", this.baseUrl);
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .put(body)
                .build();

        String requestRepr = String.format("permit.api.syncResources(%s)", requestBody);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            logResponse(requestRepr, response, responseString);
            return gson.fromJson(responseString, ResourceList.class);
        }
    }
}
