package by.itacademy.justshooter.shoppingmall.entity;

import by.itacademy.justshooter.shoppingmall.entity.enums.StreetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Builder.Default
    @OneToMany(mappedBy = "address", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<ShopOwner> shopOwners = new HashSet<>();

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Enumerated(EnumType.STRING)
    @Column(name = "street_type", nullable = false)
    private StreetType streetType;

    @Column(name = "building_number", nullable = false)
    private String buildingNumber;

    @Column(name = "office_number", nullable = false)
    private String officeNumber;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "city = " + city + ", " +
                "street = " + street + ", " +
                "streetType = " + streetType.getShortName() + ", " +
                "buildingNumber = " + buildingNumber + ", " +
                "officeNumber = " + officeNumber + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}