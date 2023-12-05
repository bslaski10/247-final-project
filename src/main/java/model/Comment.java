import java.util.UUID;

public class Comment {
    public String comment;
    public UUID userID;

    public Comment(String Comment, User user) 
    {
        this.comment = Comment;
        this.userID = user.getId();
    }

    public Comment(UUID userID, String Comment)
    {
        this.comment = Comment;
        this.userID = userID;
    }

    public void editComment(String comment) {
        this.comment = comment;
    }

    public UUID getUserID()
    {
        return this.userID;
    }

    public String getComment()
    {
        return this.comment;
    }

    public String toString()
    {
        User replyUser = UserDatabase.getInstance().getUserbyId(this.userID);
        if(replyUser != null)
        return "(" + replyUser.getUserName() + ")" + ": " + this.getComment();
        return null;
    }

}
