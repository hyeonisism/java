package reflection;

@Organism
public class Human implements Ageable {

    private String name;

    private int age;

    private int height;

    private int weight;

    @Override
    public void aging() {
        age++;
    }
}
