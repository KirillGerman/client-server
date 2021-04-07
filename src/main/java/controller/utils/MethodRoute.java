package controller.utils;

import java.util.Objects;

public class MethodRoute {
    private String method;
    private String path;

    public MethodRoute(String method, String path) {
        this.method = method;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodRoute that = (MethodRoute) o;
        return Objects.equals(method, that.method) &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, path);
    }
}