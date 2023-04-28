package ru.clevertec.ecl.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gift_certificate", schema = "giftCertificate")
@NamedQueries({
        @NamedQuery(name = GiftCertificate.FIND_GIFT_CERTIFICATE_BY_ID,
                query = "select distinct s from GiftCertificate s " +
                        "left join fetch s.tags i " +
                        "where s.id = :id"),
        @NamedQuery(name = GiftCertificate.FIND_ALL_WITH_TAG,
                query = "select distinct s from GiftCertificate s " +
                        "left join fetch s.tags i"),

//        @NamedQuery(name = GiftCertificate.FIND_GIFT_CERTIFICATE_BY_TAG_NAME,
//                query = "select gs from GiftCertificate gs " +
//                        "join  gs.tags gts " +
//                        "where gts.name LIKE CONCAT('%', :tagName, '%')")
        @NamedQuery(name = GiftCertificate.FIND_GIFT_CERTIFICATE_BY_TAG_NAME,
                query = "select gs from GiftCertificate gs " +
                        "where gs.name LIKE CONCAT('%', :tagName, '%')")
})
public class GiftCertificate implements Serializable {

    public static final String FIND_GIFT_CERTIFICATE_BY_ID = "GiftCertificate.findById";

    public static final String FIND_ALL_WITH_TAG = "GiftCertificate.findAllWithTag";

    public static final String FIND_GIFT_CERTIFICATE_BY_TAG_NAME = "GiftCertificate.findGiftCertificateByTagName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "price")
    private Float price;

    @NotNull
    @Column(name = "duration")
    private Integer duration;

    @NotNull
    @EqualsAndHashCode.Exclude
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @NotNull
    @EqualsAndHashCode.Exclude
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    /**
     * List of Tags that belong to GiftCertificate {@link Tag}.
     */

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JoinTable(
            name = "gift_certificate_tag"
            , joinColumns = @JoinColumn(name = "gift_certificate_id")
            , inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @OrderBy("id asc, name desc ")
    private List<Tag> tags;
}
