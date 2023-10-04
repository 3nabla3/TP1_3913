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
        file_name = file.getName();
        package_name = Tls.GetPackageName(file);
        class_name = file.getName().replace(".java", ""); // TODO: is this always true??
        tloc = Tloc.GetTloc(file);
        tassert = Tassert.CountAssert(file);
        tcmp = (float) tloc / (float) tassert; // TODO: il faut peut-être round up??
    }

    public String toString() {
        return String.format("%s, %s, %s, %d, %d, %f", file_name, package_name, class_name, tloc, tassert, tcmp);
    }
}
