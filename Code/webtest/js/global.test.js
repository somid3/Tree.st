
function RegistryTest () {

    R = new Registry();

    console.log(R.isParentWithChildren("parent1") + " should be 'false'");

    R.registerChild("parent1", "child1", "value1");

    console.log(R.getChildrenOfParent("parent1")["child1"] + " should be 'value1'");
    console.log(R.getChild("parent1", "child1") + " should be 'value1'");

    R.registerChild("parent1", "child1", "value1b");

    console.log(R.isParentWithChildren("parent1") + " should be 'true'");

    console.log(R.isChildRegistered("parent1", "child1") + " should be 'true'");
    console.log(R.isChildRegistered("parent1", "child2") + " should be 'false'");


    R.registerChild("parent1", "child2", "value2");
    console.log(R.getChildrenOfParent("parent1")["child2"] + " should be 'value2'");

    R.unregisterChild("parent1", "child1");
    R.unregisterChild("parent1", "child2");

    console.log(R.isParentWithChildren("parent1") + " should be 'false'");

    R.registerChild("parent1", "child3", "valu3");

    console.log(R.isParentWithChildren("parent1") + " should be 'true'");

    console.log(R.getRegistry());





    R2 = new Registry();

    console.log(R2.containsChildren() + " should be 'false'");

    R2.registerChild("parent1", "child1", "value1");

    console.log(R2.containsChildren() + " should be 'true'");

}