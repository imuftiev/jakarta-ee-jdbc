/* Лабораторная работа - 9. Вариант 69 - 131. Муфтиев Ильяс Наилевич - 4317
131 = 1000 0011

 П5 | П6 | П7 | П8 | П10-П13 | П12 | П14 | П15
 1  | 0  | 0  | 0  |   001   |  0  |  1  |  1

*/
import java.sql.*;
import java.util.Scanner;

public class DbApp {

    static boolean flag = true;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/home_library";
    private static final String USER = "admin_ilyas";
    private static final String PASSWORD = "12345";

    public static void main(String[] args) {
        while (flag) {
            System.out.println("1 - Добавить новую запись");
            System.out.println("2 - Редактировать запись по значению поля");
            System.out.println("3 - Удалить запись из таблицы по значению");
            System.out.println("4 - Вывод таблиц");
            System.out.println("5 - Очистить таблицы");
            System.out.println("6 - Завершить работу");

            Scanner scanner = new Scanner(System.in);

            int ch = scanner.nextInt();
            scanner.nextLine();
            switch (ch) {
                case 1:
                    System.out.println("В какую таблицу добавить запись: \n 1 - Author \n 2 - Library \n 0 - Отмена");
                    int ch1 = scanner.nextInt();
                    scanner.nextLine();

                    switch (ch1) {

                        case 1:
                            System.out.println("Добавление записи в таблицу Author:");
                            try {
                                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                                String sql = "INSERT INTO author (edition_name, edition_year, pages_amount, year_writing, weight) VALUES (?, ?, ?, ?, ?)";
                                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                                    System.out.print("Издательство : ");
                                    String edition_name = scanner.nextLine();
                                    System.out.print("Год издания : ");
                                    int edition_year = scanner.nextInt();
                                    System.out.print("Кол-во стр. : ");
                                    int pages_amount = scanner.nextInt();
                                    System.out.print("Год написания : ");
                                    int year_writing = scanner.nextInt();
                                    System.out.print("Вес : ");
                                    int weight = scanner.nextInt();
                                    preparedStatement.setString(1, edition_name);
                                    preparedStatement.setInt(2, edition_year);
                                    preparedStatement.setInt(3, pages_amount);
                                    preparedStatement.setInt(4, year_writing);
                                    preparedStatement.setInt(5, weight);
                                    preparedStatement.executeUpdate();
                                }
                            }
                            catch (SQLException exception) {
                                System.out.println("Error: " + exception);
                            }
                            break;

                        case 2:
                            System.out.println("Внесение записи в таблицу Library");
                            try {
                                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                                String sql = "INSERT INTO library (floor, closet, bookshelf, author_id) VALUES (?,?,?,?)";
                                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                                    System.out.print("Этаж : ");
                                    int floor = scanner.nextInt();
                                    System.out.print("Шкаф : ");
                                    int closet = scanner.nextInt();
                                    System.out.print("Полка : ");
                                    int bookshelf = scanner.nextInt();
                                    System.out.print("Id Автора : ");
                                    int author_id = scanner.nextInt();
                                    preparedStatement.setInt(1,  floor);
                                    preparedStatement.setInt(2, closet);
                                    preparedStatement.setInt(3, bookshelf);
                                    preparedStatement.setInt(4, author_id);
                                    preparedStatement.executeUpdate();
                                }
                            }
                            catch (SQLException exception) {
                                System.out.println("Error: " + exception);
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Команда не была распознана!");
                    }

                    break;

                case 2:
                    break;
                case 3:
                    System.out.println("Из какой таблицы удалить запись: \n 1 - Author \n 2 - Library \n 3 - Отмена");
                    int ch3 = scanner.nextInt();
                    scanner.nextLine();
                    switch (ch3) {
                        case 1:
                            try {
                                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                                String sql = "DELETE FROM author WHERE author_id = ? ";
                                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                                    System.out.println("Author's id: ");
                                    int authorId = scanner.nextInt();
                                    preparedStatement.setInt(1, authorId);
                                    preparedStatement.executeUpdate();
                                }
                                System.out.println("Запись удалена");
                            }
                            catch (SQLException exception) {
                                System.out.println("Error:" + exception);
                            };
                            break;
                        case 2:
                            try {
                                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                                String sql = "DELETE FROM library WHERE library_id = ? ";
                                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                                    System.out.println("Library's id: ");
                                    int authorId = scanner.nextInt();
                                    preparedStatement.setInt(1, authorId);
                                    preparedStatement.executeUpdate();
                                }
                                System.out.println("Запись удалена");
                            }
                            catch (SQLException exception) {
                                System.out.println("Error:" + exception);
                            };
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Команда не была распознана!");
                    }
                    break;
                case 4:
                    System.out.println("\nAUTHOR TABLE\n");
                    String columnsAuthorName = "id | издательство | год изд. | кол-во стр. | год написания | вес |";
                    System.out.println(columnsAuthorName);
                    try {
                        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                        Statement statement = connection.createStatement();
                        String sql = "SELECT author_id, edition_name, edition_year, pages_amount, year_writing, weight FROM author";
                        ResultSet resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            int id = resultSet.getInt("author_id");
                            String edition_name = resultSet.getString("edition_name");
                            int edition_year = resultSet.getInt("edition_year");
                            int pages_amount = resultSet.getInt("pages_amount");
                            int year_writing = resultSet.getInt("year_writing");
                            int weight = resultSet.getInt("weight");
                            String output = "-";
                            String repeatedOutput = output.repeat(columnsAuthorName.length());
                            System.out.println(repeatedOutput);
                            System.out.format("%-2d | %-12s | %-8d | %-11d | %-13d | %-3d | %n", id, edition_name, edition_year, pages_amount, year_writing, weight);
                        }
                    }
                    catch (SQLException exception) {
                        System.out.println("Error: " + exception);
                    }
                    System.out.println("\nLIBRARY TABLE\n");
                    String columnsLibraryName = "id | этаж | шкаф | полка | id автора |";
                    System.out.println(columnsLibraryName);
                    try {
                        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                        Statement statement = connection.createStatement();
                        String sql = "SELECT library_id, floor, closet, bookshelf, author_id FROM library ";
                        ResultSet resultSet = statement.executeQuery(sql);
                        while (resultSet.next()) {
                            int id = resultSet.getInt("library_id");
                            int floor = resultSet.getInt("floor");
                            int closet = resultSet.getInt("closet");
                            int bookshelf = resultSet.getInt("bookshelf");
                            int author_id = resultSet.getInt("author_id");
                            String output = "-";
                            String repeatedOutput = output.repeat(columnsLibraryName.length());
                            System.out.println(repeatedOutput);
                            System.out.format("%-2d | %-4s | %-4d | %-5d | %-9d | %n", id, floor, closet, bookshelf, author_id);
                        }
                    }
                    catch (SQLException exception) {
                        System.out.println("Error: " + exception);
                    }
                    System.out.println("\n");
                    break;
                case 5:
                    try {
                        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                        Statement statement = connection.createStatement();
                        String sql = "TRUNCATE author, library RESTART IDENTITY ";
                        statement.executeUpdate(sql);
                    }
                    catch (SQLException exception) {
                        System.out.println("Error: " + exception);
                    }
                    System.out.println("\nТаблицы очищены");
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Команда не была распознана!");
            }
        }
    }
}