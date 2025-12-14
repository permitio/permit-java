.PHONY: help

.DEFAULT_GOAL := help

# ENVIRONMENT?=dev

## generate openapi models
generate-openapi:
	openapi-generator generate -i https://api.permit.io/v2/openapi.json -g java -o generated/ -c openapi-config.json --skip-validate-spec

clean-openapi:
	rm -rf generated/

## generate open api models from json schema
generate-jsonschema:
    openapi2jsonschema https://api.permit.io/v2/openapi.json -o schemas/

