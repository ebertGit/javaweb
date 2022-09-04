package org.example.servlet.method;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RequestMappingInfo implements Comparable<RequestMappingInfo> {
    private final String path;
    private final String[] method;

    /**
     * Constructor method.
     * @param path request path(non-empty).
     * @param method http method(nullable).
     */
    public RequestMappingInfo(String path, String[] method) {
        if (path == null || path.trim().length() == 0) {
           throw new RuntimeException("path can not be empty.");
        }
        this.path = path;
        if (method != null) {
            this.method = method;
            Arrays.sort(this.method);
        } else {
            this.method = new String[0];
        }
    }

    public String getPath() {
        return this.path;
    }

    public List<String> getMethod() {
        return Arrays.asList(this.method);
    }

    // TODO no need to override...
    @Override
    public int compareTo(RequestMappingInfo o) {
        return doCompare(o);
    }

    private int doCompare(RequestMappingInfo o) {
        if (o == null) {
            return 1;
        }

        if (this.path.equals(o.getPath())) {
            List<String> methods1 = this.getMethod();
            // when current class's method is empty, it means match all.
            if (methods1.size() == 0) {
                return 0;
            }

            List<String> methods2 = o.getMethod();
            // when current class's method is not empty and the method of compared class is empty,
            // it means current class is not match it.
            if (methods2.size() == 0) {
                return -1;
            }

            // if current class's method is contains all of the method of compared class,
            // it means current class is match it.
            if (methods1.containsAll(methods2)) {
                return 0;
            }

            // if both methods are not empty, then compare there's string.
//            methods1.sort(Comparator.naturalOrder());
//            methods2.sort(Comparator.naturalOrder());
            return methods1.toString().compareTo(methods2.toString());
        }

        return this.path.compareTo(o.getPath());
    }

//    @Override
//    public boolean equals(Object o) {
//        return doCompare((RequestMappingInfo) o) == 0;
//    }

//    @Override
//    public int hashCode() {
//        int result = Objects.hash(path);
//        result = 31 * result + Arrays.hashCode(method);
//        return result;
//    }

    @Override
    public String toString() {
        return "RequestMappingInfo{" +
                "path='" + path + '\'' +
                ", method=" + Arrays.toString(method) +
                '}';
    }
}
