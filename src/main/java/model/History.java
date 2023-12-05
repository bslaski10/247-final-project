class History {
    private String change;
    private String date;
    private User user;

    public History(String change, String date, User user) {
        this.change = change;
        this.date = date;
        this.user = user;
    }

    public String getChange() {
        return change;
    }

    public String getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}