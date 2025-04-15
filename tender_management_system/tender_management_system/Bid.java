import java.sql.*;

public class Bid {
    private User user;
    private double amount;

    public Bid(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public static boolean placeBid(String tenderId, User user, double amount) {
        String query = "INSERT INTO bids (tender_id, user_id, amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenderId);
            stmt.setInt(2, user.getId());
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
