package life.joker.community.cache;

import life.joker.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author joker
 * @date 2023/03/08 15:58
 **/
public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO frontEnd = new TagDTO();
        frontEnd.setCategoryName("前端");
        frontEnd.setTags(Arrays.asList("前端", "javascript", "typescript", "ecmascript-6", "CSS", "Css3", "html", "html5", "node.js", "npm", "react.js", "vue.js", "angular", "chrome", "chrome-devtools", "safari", "webkit", "edge", "bootstrap", "tailwind-css", "antd", "sass", "less", "postcss", "yarn", "postman", "fiddler"));
        TagDTO backEnd = new TagDTO();
        backEnd.setCategoryName("后端");
        backEnd.setTags(Arrays.asList("后端", "go", "php", "lavarel", "swoole", "java", "spring", "springboot", "node.js", "express", "python", "flask", "django", "fastapi", "C", "C++", "c#", "ruby", "ruby-on-rails", "asp.net", "scala", "rust", "爬虫"));
        TagDTO mobile = new TagDTO();
        mobile.setCategoryName("移动端");
        mobile.setTags(Arrays.asList("android", "android-studio", "java", "kotlin", "ios", "swift", "objective-c", "xcode", "flutter", "react-native", "webapp", "小程序"));
        TagDTO database = new TagDTO();
        database.setCategoryName("数据库");
        database.setTags(Arrays.asList("数据库", "mysql", "mariadb", "postgresql", "sqlite", "sql", "nosql", "redis", "mongodb", "json", "yaml", "xml", "elasticsearch", "memcached"));
        TagDTO operation = new TagDTO();
        operation.setCategoryName("运维");
        operation.setTags(Arrays.asList("运维", "微服务", "服务器", "linux", "ubuntu", "debian", "nginx", "apache", "docker", "容器", "kubernetes", "devops", "serverless", "负载均衡", "ssh", "jenkins", "vagrant"));
        TagDTO ai = new TagDTO();
        ai.setCategoryName("人工智能");
        ai.setTags(Arrays.asList("算法", "机器学习人工智能", "深度学习", "数据挖掘", "tensorflow", "pytorch", "神经网络", "图像识别", "人脸识别", "自然语言处理", "机器人", "自动驾驶"));
        TagDTO tool = new TagDTO();
        tool.setCategoryName("工具");
        tool.setTags(Arrays.asList("编辑器", "git", "github", "visual-studio-code", "visual-studio", "intellj-idea", "sublime -text", "webstorm", "pycharm", "goland", "phpstorm", "vim", "emacs"));
        TagDTO other = new TagDTO();
        other.setCategoryName("其他");
        other.setTags(Arrays.asList("程序员", "segmentfault", "restful", "graphql", "安全", "XSS", "csrf", "rpc", "http", "https", "udp", "websocket", "比特币", "以太坊", "智能合约", "区块链", "leetcode"));
        tagDTOS.add(frontEnd);
        tagDTOS.add(backEnd);
        tagDTOS.add(mobile);
        tagDTOS.add(database);
        tagDTOS.add(operation);
        tagDTOS.add(ai);
        tagDTOS.add(tool);
        tagDTOS.add(other);
        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
