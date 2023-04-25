package in.ezeon;

public class Category {
//    This will generate unique id automatically every time you create object
//    We are using Long instead of long because we want to treat value as an objet.
    private Long categoryId = System.currentTimeMillis();
    private String name;

    public Category(String name) {
        this.name = name;
    }


    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
