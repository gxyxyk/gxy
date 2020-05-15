package lrm.com.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

/**
 * 文本转化
 */
public class MarkdownUtils{
    public static String markdownToHtmlExtensions(String markdown){
        //h标签生成id
        Set<Extension> he = Collections.singleton(HeadingAnchorExtension.create());
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();
        Node node = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(he).
                extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
            public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                return new CustomAttributeProvider();
            }
        }).build();
        return renderer.render(node);
    }
    static class  CustomAttributeProvider implements AttributeProvider{
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            if (node instanceof Link){
                map.put("target","_blank");
            }
            if (node instanceof TableBlock){
                map.put("class","ui celled table");
            }
        }
    }
}
