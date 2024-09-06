package service;

public class ManPage {
    private static final String MAN_PAGE_PATH = "/ManPage.txt";

    /**
     * Displays the help page content to the console.
     * This method reads the man page content and prints it to the standard output.
     * If an error occurs while loading the help page, an error message is printed to the standard error stream.
     */
    public static void showHelpPage() {
        try {
            String manPageContent = readManPage();
            System.out.println(manPageContent);
        } catch (Exception e) {
            System.err.println("Error loading help page: " + e.getMessage());
        }
    }

    /**
     * Reads the content of the man page from the resource file.
     *
     * @return A String containing the content of the man page.
     * @throws Exception If the man page resource cannot be found or read.
     */
    public static String readManPage() throws Exception {
        try (var inputStream = ManPage.class.getResourceAsStream(MAN_PAGE_PATH)) {
            if (inputStream == null) {
                throw new Exception("ManPage not found");
            }
            return new String(inputStream.readAllBytes());
        }
    }
}
