package ru.example.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ExampleChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ExampleEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ExampleSubChildEntity> children;

    private int value;

    public ExampleChildEntity(int value) {
        this.value = value;
    }

    public ExampleChildEntity(int value, List<ExampleSubChildEntity> children) {
        this(value);
        this.children = children;

        children.forEach(child -> child.setParent(this));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExampleEntity getParent() {
        return parent;
    }

    public void setParent(ExampleEntity parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<ExampleSubChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ExampleSubChildEntity> children) {
        this.children = children;
    }
}
