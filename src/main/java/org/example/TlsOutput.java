package org.example;

import java.io.File;

public class TlsOutput {
    String file_name;
    String package_name;
    String class_name;
    int tloc;
    int tassert;
    float tcmp;

    public TlsOutput(File file) {
        file_name = file.getPath();
        package_name = Tls.GetPackageName(file);
        class_name = file.getName().replace(".java", "");
        tloc = Tloc.GetTloc(file);
        tassert = Tassert.CountAssert(file);
        tcmp = (float) tloc / (float) tassert;
    }

    /**
     * Returns whether the file contains a test. We know it does if there is at least
     * one assert statement.
     *
     * @return true if the file contains a test, false otherwise
     */
    public boolean ContainsTest() {
        return tassert > 0;
    }

    public String toString() {
        return String.format("%s, %s, %s, %d, %d, %f", file_name, package_name, class_name, tloc, tassert, tcmp);
    }
}
