package service;

public class ManPage {
    private static final String MAN_PAGE_PATH = "/ManPage.txt";

    public static void showHelpPage() {
        try {
            String manPageContent = readManPage();
            System.out.println(manPageContent);
        } catch (Exception e) {
            System.err.println("Error loading help page: " + e.getMessage());
        }
    }

    public static String readManPage() throws Exception {
        try (var inputStream = ManPage.class.getResourceAsStream(MAN_PAGE_PATH)) {
            if (inputStream == null) {
                throw new Exception("ManPagenot found");
            }
            return new String(inputStream.readAllBytes());
        }
    }
}
