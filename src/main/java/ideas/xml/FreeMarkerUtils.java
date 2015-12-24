package ideas.xml;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;

import java.io.*;
import java.util.Map;

public final class FreeMarkerUtils {
    private static FreeMarkerUtils INSTANCE = new FreeMarkerUtils();

    private Configuration cfg = new Configuration();

    private FreeMarkerUtils() {
        try {
            cfg = new Configuration();

            ClassTemplateLoader ctl = new ClassTemplateLoader(FreeMarkerUtils.class, "/");
            FileTemplateLoader ftl1;
            ftl1 = new FileTemplateLoader(new File("."));
            TemplateLoader[] loaders = new TemplateLoader[]{ctl, ftl1};
            MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);

            cfg.setTemplateLoader(mtl);
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        } catch (IOException e) {
            System.err.println("Unable to set file template loader to current directory");
        }
    }

    public static String template(Map<String, Object> dataModel, String templateName) throws TemplateException {
        try {
            Template template = INSTANCE.cfg.getTemplate(templateName);
            return processTemplate(dataModel, template);
        } catch (IOException ex) {
            throw new TemplateException(ex, null);
        }
    }

    private static String processTemplate(Map<String, Object> dataModel, Template template) throws IOException, TemplateException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (Writer out = new OutputStreamWriter(baos)) {
            template.process(dataModel, out);
            return baos.toString();
        }
    }
}