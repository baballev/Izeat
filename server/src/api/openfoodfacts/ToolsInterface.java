package api.openfoodfacts;

import java.lang.StringBuilder;

public interface ToolsInterface {
	// Class inspired by the official Python release of the OpenFoodFacts' SDK.
	// What it does: Gives methods to retrieve queries result from OpenFoodFacts as
	// a StringBuilder object. This StringBuilder contains the JSON file's content.
	// What it does not: Format data so that it is ready to be used.

	// code is a 12 digits integer corresponding to the barcode of the desired
	// product. It is represented as a string as every 0 at the begining matters.
	// Returns a StringBuilder containing the JSON file.
	public static StringBuilder getProductQuery(String code) {
		return null;
	}

	// query corresponds to the search field.
	// pageNumber corresponds to the desired page number of the research result.
	// pageSize corresponds to the number of products by page.
	// Returns a StringBuilder containing the HTML doc.
	public static StringBuilder getSearchQuery(String query, int pageNumber, int pageSize) {
		return null;
	}

}
