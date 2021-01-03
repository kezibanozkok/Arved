package compeng.arved.domain;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

public class Photo {

    @Id
    private String id;
    private String title;
    private Binary image;
}
