package ru.clevertec.ecl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag", schema = "public")
@NamedQueries({
        @NamedQuery(name = Tag.FIND_TAG_BY_TAG_NAME,
                query = "select gs from Tag gs " +
                        "where gs.name LIKE CONCAT('%', :tagName, '%')")
})
public class Tag implements Serializable {

    public static final String FIND_TAG_BY_TAG_NAME = "Tag.findTagByTagName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    /**
     * List of GiftCertificates that belong to the tag {@link GiftCertificate}.
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags", fetch = FetchType.EAGER)
    @OrderBy("createDate asc, name desc")
    private List<GiftCertificate> giftCertificates;
}
