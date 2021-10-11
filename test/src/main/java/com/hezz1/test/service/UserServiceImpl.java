package com.hezz1.test.service;

import com.hezz1.test.model.User;
import org.springframework.stereotype.Service;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;


@Service
public class UserServiceImpl implements UserService {
    private final MongoCollection<Document> collection=MongoClients.create("mongodb://192.168.174.128:27017")
            .getDatabase("test").getCollection("user");

    @Override
    public String addUser(User user) {
        try {
            String id=user.getId();
            String name=user.getName();
            int age=user.getAge();
            if (id == null || id.length() < 1 || name == null || name.length() < 1 || age == 0) {
                return "新增失败";
            }
            collection.insertOne(
                    new Document("id", id).append("name", name).append("age", age));
            return "新增成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "新增失败";
        }
    }

    @Override
    public String getUser(String id, String name, String age) {
        Bson filter;//查找条件器
        if (id == null && (name == null || name.equals(""))) {//没有参数
            return ("get参数请求错误！");
        }
        if (id != null && id != null && !name.equals("")) {//有三个参数
            filter=Filters.and(Filters.eq("age", Integer.parseInt(age)), Filters.regex("name", name),
                    Filters.regex("id", id));
        } else {
            if (id != null) {//只有id模糊匹配
                filter=Filters.regex("id", id);
            } else if (name != null) {//只有name精确匹配
                filter=Filters.regex("name", name);
            } else {//只有age精确匹配
                filter=Filters.eq("age", Integer.parseInt(age));//转int
            }
        }
        FindIterable<Document> documentList=collection.find(filter);
        StringBuilder stringBuilder=new StringBuilder();
        for (Document document : documentList) {//全部结果打印
            stringBuilder.append("id:").append(document.get("id"))
                    .append(",name:").append(document.get("name"))
                    .append(", age:").append(document.get("age")).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String deluser(String id, String name, String age) {
    }

    @Override
    public String changeUser(String id, String name, String age) {
    }

}

