import java.util.List;
import java.util.Scanner;
// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than the instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

// -------------------------------------------------------------------------
/**
 * The main program that receives commands from users
 *
 * @author Danh Nguyen (danh0902)
 * @version Feb 10, 2014
 */
public class bst
{
    // Variables used for reading input
    private static String              name;
    private static String              minName;
    private static String              maxName;
    private static String              payload;
    private static String              field;
    private static String              command;
    private static int                 population;
    private static int                 minPopulation;
    private static int                 maxPopulation;
    private static int                 k;
    private static City                city;
    private static City                minCity;
    private static City                maxCity;
    private static City                tmp;
    private static List<City>          list;
    private static List<StringBuilder> space;
    private static String[]            token;

    // The two binary search trees
    private static DatabaseBST<City>   nameBST;
    private static DatabaseBST<City>   populationBST;


    // ----------------------------------------------------------
    /**
     * Echo a command to output with its parameters
     */
    public static void printCommand()
    {
        System.out.print(token[0]);

        for (int i = 1; i < token.length; ++i)
        {
            System.out.print(" " + token[i]);
        }

        System.out.println();
    }


    // ----------------------------------------------------------
    /**
     * Insert a record to the database
     */
    public static void insert()
    {
        printCommand();

        name = token[1];
        population = Integer.valueOf(token[2]);
        payload = token[3];
        city = new City(name, population, payload);
        nameBST.insert(city);
        populationBST.insert(city);
    }


    // ----------------------------------------------------------
    /**
     * Find records in the database
     */
    public static void find()
    {
        printCommand();

        field = token[1];

        // Do a find
        if (field.equals("name"))
        {
            name = token[2];
            city = new City(name, -1, "-1");

            list = nameBST.find(city);
        }

        if (field.equals("population"))
        {
            population = Integer.valueOf(token[2]);
            city = new City("-1", population, "-1");

            list = populationBST.find(city);
        }

        // Now print them out
        if (list == null)
        {
            System.out.println("Not found");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                System.out.println(list.get(i).toString());
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Find the k-th record in the database
     */
    public static void findKth()
    {
        printCommand();

        field = token[1];
        k = Integer.valueOf(token[2]);

        // Do a find
        if (field.equals("name"))
        {
            tmp = nameBST.findKth(k);
        }

        if (field.equals("population"))
        {
            tmp = populationBST.findKth(k);
        }

        // Now print them out
        if (tmp == null)
        {
            System.out.println("Not found");
        }
        else
        {
            System.out.println(tmp.toString());
        }
    }


    // ----------------------------------------------------------
    /**
     * Find the records whose values are in a given range
     */
    public static void findRange()
    {
        printCommand();

        field = token[1];

        // Do a find
        if (field.equals("name"))
        {
            minName = token[2];
            maxName = token[3];
            minCity = new City(minName, -1, "-1");
            maxCity = new City(maxName, -1, "-1");

            list = nameBST.findRange(minCity, maxCity);
        }

        if (field.equals("population"))
        {
            minPopulation = Integer.valueOf(token[2]);
            maxPopulation = Integer.valueOf(token[3]);
            minCity = new City("-1", minPopulation, "-1");
            maxCity = new City("-1", maxPopulation, "-1");

            list = populationBST.findRange(minCity, maxCity);
        }

        // Now print them out
        if (list == null)
        {
            System.out.println("Not found");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                System.out.println(list.get(i).toString());
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Remove specified records out of both trees
     */
    public static void delete()
    {
        printCommand();

        field = token[1];

        // Find all the duplicates with the given value
        if (field.equals("name"))
        {
            name = token[2];
            city = new City(name, -1, "-1");

            list = nameBST.find(city);
        }

        if (field.equals("population"))
        {
            population = Integer.valueOf(token[2]);
            city = new City("-1", population, "-1");

            list = populationBST.find(city);
        }

        // Now delete them on both trees
        if (list == null)
        {
            System.out.println("Not found");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                nameBST.delete(list.get(i));
                populationBST.delete(list.get(i));
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Prints out the records in ascending order according to a particular field
     */
    public static void sort()
    {
        printCommand();

        field = token[1];

        // Do a traverse
        if (field.equals("name"))
        {
            list = nameBST.sort();
        }

        if (field.equals("population"))
        {
            list = populationBST.sort();
        }

        // Now print them out
        if (list == null)
        {
            System.out.println("Database empty");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                System.out.println(list.get(i).toString());
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Prints out the records in ascending order according to a particular field
     * plus some additional spaces
     */
    public static void tree()
    {
        printCommand();

        field = token[1];

        // Do a traverse
        if (field.equals("name"))
        {
            list = nameBST.sort();
            space = nameBST.tree();
        }

        if (field.equals("population"))
        {
            list = populationBST.sort();
            space = populationBST.tree();
        }

        // Now print them out
        if (list == null)
        {
            System.out.println("Database empty");
        }
        else
        {
            for (int i = 0; i < list.size(); ++i)
            {
                System.out.print(space.get(i));
                System.out.println(list.get(i).toString());
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Remove all of the records out of the database
     */
    public static void makeNull()
    {
        printCommand();

        nameBST.makeNull();
        populationBST.makeNull();
    }


    // ----------------------------------------------------------
    /**
     * Read commands from users and return results back to them. Compiler:
     * Eclipse - ADT Bundle, Operating System: Windows 7
     *
     * @version Feb 10, 2014
     * @param args
     *            program invocation and its parameters
     */
    public static void main(String[] args)
    {
        nameBST = new DatabaseBST<City>(new NameComp());
        populationBST = new DatabaseBST<City>(new PopulationComp());

        Scanner scan = new Scanner(System.in);

        while (scan.hasNextLine())
        {
            command = scan.nextLine();
            String delims = "\\s+";
            token = command.trim().split(delims);

            if (token[0].equals("insert"))
            {
                insert();
            }

            if (token[0].equals("find"))
            {
                find();
            }

            if (token[0].equals("findKth"))
            {
                findKth();
            }

            if (token[0].equals("findRange"))
            {
                findRange();
            }

            if (token[0].equals("delete"))
            {
                delete();
            }

            if (token[0].equals("sort"))
            {
                sort();
            }

            if (token[0].equals("tree"))
            {
                tree();
            }

            if (token[0].equals("makenull"))
            {
                makeNull();
            }
        }

        scan.close();
    }
}
