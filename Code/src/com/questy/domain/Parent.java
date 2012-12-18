package com.questy.domain;

public abstract  class Parent implements Comparable<Parent> {

    private Integer id;

    public Parent() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals (Object obj) {

        boolean out = false;

        if (canEqual(obj)) {

            Parent parent = (Parent) obj;

            if (id == null || parent.getId() == null)

                out = false;

            else if (id.equals(parent.getId()))

                out = true;

        }

        return out;
    }

    @Override
    public int hashCode() {
        return (41 * id.hashCode());
        
    }

    public boolean canEqual(Object other) {

        /* "instanceof" takes care of null objects
           by returning false */
        return (other instanceof Parent);
    }

    public int compareTo( Parent parent ) {

        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if ( this == parent ) return EQUAL;

        if (id < parent.getId()) return BEFORE;
        if (id > parent.getId()) return AFTER;

        return EQUAL;
    }
}
