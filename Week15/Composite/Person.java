package Week15.Composite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {
    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    private String name;
    private Date birthday;
    private String sex;
    private Person partner;
    private List<Person> children;

    public Person(String name, Date birthday, String sex) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.partner = null;
        this.children = new ArrayList<Person>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Person getPartner() {
        return this.partner;
    }

    public void setPartner(Person newPartner) {
        if (newPartner.getPartner() != null) {
            System.out.println(this.name + "'s partner has already married");
            return;
        } else if (this.getPartner() != null) {
            System.out.println(this.name + " have already married");
            return;
        } else {
            this.partner = newPartner;
            newPartner.partner = this;
        }
    }

    public List<Person> getChildren() {
        return this.children;
    }

    public void addChildren(Person child) {
        if (child == null) {
            return;
        }
        if (children.contains(child)) {
            return;
        }
        children.add(child);
    }

    public static List<Person> findUnmarriedIndividuals(List<Person> familyTree) {
        List<Person> unmarriedIndividuals = new ArrayList<>();
        for (Person person : familyTree) {
            if (person.getPartner() == null) {
                unmarriedIndividuals.add(person);
            }
        }
        return unmarriedIndividuals;
    }

    public static List<Person[]> findCouplesWithTwoChildren(List<Person> familyTree) {
        List<Person[]> couplesWithTwoChildren = new ArrayList<>();
        for (Person person : familyTree) {
            Person partner = person.getPartner();
            if (partner != null && person.getChildren().size() == 2 && partner.getChildren().size() == 2) {
                Person[] couple = { person, partner };
                boolean isExists = false;
                for (Person[] persons : couplesWithTwoChildren) {
                    if (persons[0] == couple[1] && persons[1] == couple[0]) {
                        isExists = true;
                    }
                }
                if (!isExists) {
                    couplesWithTwoChildren.add(couple);
                }
            }
        }
        return couplesWithTwoChildren;
    }

    public static List<List<Person>> findLatestGenerations(List<Person> familyTree) {
        List<List<Person>> latestGenerations = new ArrayList<>();
        List<Person> currentGeneration = new ArrayList<>(familyTree);

        while (!currentGeneration.isEmpty()) {
            latestGenerations.add(currentGeneration);
            List<Person> nextGeneration = new ArrayList<>();

            for (Person person : currentGeneration) {
                for (Person child : person.getChildren()) {
                    if (!nextGeneration.contains(child)) {
                        nextGeneration.add(child);
                    }
                }
            }

            currentGeneration = nextGeneration;
        }

        return latestGenerations;
    }

    public static List<Person> createFamilyTree() {
        List<Person> familyTree = new ArrayList<>();

        Person person1 = new Person("John", new Date(), Person.MALE);
        Person person2 = new Person("Jane", new Date(), Person.FEMALE);
        Person person3 = new Person("Adam", new Date(), Person.MALE);
        Person person4 = new Person("Eve", new Date(), Person.FEMALE);
        Person person5 = new Person("Tom", new Date(), Person.MALE);
        Person person6 = new Person("Kate", new Date(), Person.FEMALE);
        Person person7 = new Person("Mike", new Date(), Person.MALE);
        Person person8 = new Person("Lisa", new Date(), Person.FEMALE);

        person1.setPartner(person2);
        person3.setPartner(person4);
        person5.setPartner(person6);

        person1.addChildren(person5);
        person2.addChildren(person5);

        person3.addChildren(person7);
        person4.addChildren(person7);
        person3.addChildren(person8);
        person4.addChildren(person8);

        familyTree.add(person1);
        familyTree.add(person2);
        familyTree.add(person3);
        familyTree.add(person4);
        familyTree.add(person5);
        familyTree.add(person6);
        familyTree.add(person7);
        familyTree.add(person8);

        return familyTree;
    }

    public static void main(String[] args) {
        List<Person> familyTree = createFamilyTree();

        List<Person> unmarriedIndividuals = findUnmarriedIndividuals(familyTree);
        System.out.println("Các cá nhân không kết hôn:");
        for (Person person : unmarriedIndividuals) {
            System.out.println(person.getName());
        }

        List<Person[]> couplesWithTwoChildren = findCouplesWithTwoChildren(familyTree);
        System.out.println("Các cặp vợ chồng có hai con:");
        for (Person[] couple : couplesWithTwoChildren) {
            System.out.println(couple[0].getName() + " & " + couple[1].getName());
        }

        List<List<Person>> latestGenerations = findLatestGenerations(familyTree);

        List<Person> generation = latestGenerations.get(latestGenerations.size() - 1);
        System.out.println("Thế hệ gần nhất: ");
        for (Person person : generation) {
            System.out.println(person.getName());
        }

    }
}
