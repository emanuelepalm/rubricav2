package utils;

import java.io.File;
import java.io.FilenameFilter;

class ExtensionFilter implements FilenameFilter
{
    private String extension;
    public ExtensionFilter(String extension) {
        this.extension = extension;
    }
    @Override
    public boolean accept(File dir, String fileName)
    {
        return (fileName.endsWith(extension));
    }
}
