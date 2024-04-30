package com.csc340.CIC.comment;

/**
 * Utility class to format bill IDs for consistent use throughout the application.
 */
public class BillIdFormatter {

    /**
     * Formats a bill ID by converting it to lower case and removing all periods.
     * @param billId the original bill ID
     * @return a formatted bill ID suitable for URLs and display
     */
    public static String formatBillId(String billId) {
        if (billId != null) {
            // Convert all characters to lower case and remove any periods.
            return billId.toLowerCase().replace(".", "");
        }
        return null;
    }
}
