package testDataAndUtils;

public enum Endpoints {
    AddPlace("maps/api/place/add/json"),
    GetPlace("maps/api/place/get/json"),
    DeletePlace("maps/api/place/delete/json");

    private String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
