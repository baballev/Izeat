package fr.izeat.api.openfoodfacts;

public interface ToolsInterface {

    /* What it does: Gives methods to retrieve queries result from OpenFoodFacts as
     * a  String. This String contains the JSON file's content.
     * What it does not: Format data so that it is ready to be used. See Product class for that purpose.
     *
     * code is a 12-13 digits integer corresponding to the barcode of the desired
     * product. It is represented as a string as every 0 at the begining matters.
     * Returns a String containing the JSON file.
     * If the method fails to get the JSON file, it will return null.
     */
    public static String getProductQuery(String code) {
        return null;
    }

    /* query corresponds to the search field.
     * pageNumber corresponds to the desired page number of the research result.
     * pageSize corresponds to the number of products by page.
     * Returns a String containing the JSON file with every products that matched the research terms.
     * If the method fails to get the JSON doc, it will return null.
     */
    public static String getSearchQuery(String query, int pageNumber, int pageSize) {
        return null;
    }

}
