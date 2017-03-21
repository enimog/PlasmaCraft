package plasmacraft.entity;

public class EntityManager
{
    private static int currentID = 0;
    
    public static int getNextEntityID()
    {
        return currentID++;
    }
}
