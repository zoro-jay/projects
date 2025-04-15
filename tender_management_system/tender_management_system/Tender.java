import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Tender {
    private String tenderId;
    private String description;
    private List<Bid> bids;

    public Tender(String tenderId, String description) {
        this.tenderId = tenderId;
        this.description = description;
        this.bids = new ArrayList<>();
    }

    public String getTenderId() {
        return tenderId;
    }

    public String getDescription() {
        return description;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public static boolean createTender(String tenderId, String description) {
        String query = "INSERT INTO tenders (tender_id, description) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenderId);
            stmt.setString(2, description);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Tender> getAllTenders() {
        List<Tender> tenders = new ArrayList<>();
        String query = "SELECT * FROM tenders";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String tenderId = rs.getString("tender_id");
                String description = rs.getString("description");
                Tender tender = new Tender(tenderId, description);
                tenders.add(tender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenders;
    }
}
