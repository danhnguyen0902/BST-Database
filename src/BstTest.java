import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the bst class
 *
 * @author Danh Nguyen (danh0902)
 * @version Feb 10, 2014
 */
public class BstTest
    extends TestCase
{
    // ----------------------------------------------------------
    /**
     * Tests the main() function
     */
    public void testMain()
    {
        // 1
        bst obj = new bst();
        assertNotNull(obj);

        // 2

        setSystemIn("insert b 5 stuff\n" + "insert b 0 stuff\n"
            + "insert a 4 stuff\n" + "insert c 4 stuff\n"
            + "insert d 3 stuff\n" + "insert d 8 stuff\n"
            + "insert d 6 stuff\n" + "insert a 1 stuff\n" + "find name a\n"
            + "find name e\n" + "find population 4\n"
            + "find population       2\n" + "sort name\n" + "sort population\n"
            + "tree name\n" + "tree population\n"
            + "findRange population 0 8\n" + "findRange name d a\n"
            + "findKth name 7\n" + "findKth population 5\n\n"
            + "delete name a\n" + "sort population\n" + "delete population 4\n"
            + "sort name\n\n" + "makenull\n");
        bst.main(null);
        assertTrue(systemOut().getHistory().contains(
            "insert b 5 stuff\n" + "insert b 0 stuff\n" + "insert a 4 stuff\n"
                + "insert c 4 stuff\n" + "insert d 3 stuff\n"
                + "insert d 8 stuff\n" + "insert d 6 stuff\n"
                + "insert a 1 stuff\n" + "find name a\n" + "a 4 stuff\n"
                + "a 1 stuff\n" + "find name e\n" + "Not found\n"
                + "find population 4\n" + "a 4 stuff\n" + "c 4 stuff\n"
                + "find population 2\n" + "Not found\n" + "sort name\n"
                + "a 4 stuff\n" + "a 1 stuff\n" + "b 5 stuff\n" + "b 0 stuff\n"
                + "c 4 stuff\n" + "d 3 stuff\n" + "d 8 stuff\n" + "d 6 stuff\n"
                + "sort population\n" + "b 0 stuff\n" + "a 1 stuff\n"
                + "d 3 stuff\n" + "a 4 stuff\n" + "c 4 stuff\n" + "b 5 stuff\n"
                + "d 6 stuff\n" + "d 8 stuff\n" + "tree name\n"
                + "    a 4 stuff\n" + "        a 1 stuff\n" + "b 5 stuff\n"
                + "    b 0 stuff\n" + "        c 4 stuff\n"
                + "            d 3 stuff\n" + "                d 8 stuff\n"
                + "                    d 6 stuff\n" + "tree population\n"
                + "    b 0 stuff\n" + "                a 1 stuff\n"
                + "            d 3 stuff\n" + "        a 4 stuff\n"
                + "            c 4 stuff\n" + "b 5 stuff\n"
                + "        d 6 stuff\n" + "    d 8 stuff\n"
                + "findRange population 0 8\n" + "b 0 stuff\n" + "a 1 stuff\n"
                + "d 3 stuff\n" + "a 4 stuff\n" + "c 4 stuff\n" + "b 5 stuff\n"
                + "d 6 stuff\n" + "d 8 stuff\n" + "findRange name d a\n"
                + "Not found\n" + "findKth name 7\n" + "d 6 stuff\n"
                + "findKth population 5\n" + "b 5 stuff\n" + "delete name a\n"
                + "sort population\n" + "b 0 stuff\n" + "d 3 stuff\n"
                + "c 4 stuff\n" + "b 5 stuff\n" + "d 6 stuff\n" + "d 8 stuff\n"
                + "delete population 4\n" + "sort name\n" + "b 5 stuff\n"
                + "b 0 stuff\n" + "d 3 stuff\n" + "d 8 stuff\n" + "d 6 stuff\n"
                + "makenull\n"));

        // 3
        setSystemIn("findKth name 100\n" + "delete name z\n" + "sort name\n"
            + "tree name\n");

        bst.main(null);

        assertTrue(systemOut().getHistory().contains(
            "findKth name 100\n" + "Not found\n" + "delete name z\n"
                + "Not found\n" + "sort name\n" + "Database empty\n"
                + "tree name\n" + "Database empty\n"));
    }
}
