package io.permit.sdk.util;

import java.util.ArrayList;

interface ContextTransform {
    public Context mutate(Context context);
}

public class ContextStore {
    private Context baseContext = new Context();
    private ArrayList<ContextTransform> transforms = new ArrayList<ContextTransform>();

    // override context keys
    public void add(Context context) {
        this.baseContext.putAll(context);
    }

    public void registerTransform(ContextTransform transform) {
        this.transforms.add(transform);
    }

    public Context getDerivedContext(Context context) {
        Context derivedContext = new Context();
        derivedContext.putAll(baseContext);
        derivedContext.putAll(context);
        return derivedContext;
    }

    public Context transform(Context initialContext) {
        Context context = new Context();
        context.putAll(initialContext);

        for (final ContextTransform transformFunction : transforms) {
            Context nextContext = transformFunction.mutate(context);
            context.clear();
            context.putAll(nextContext);
        }
        return context;
    }
}
