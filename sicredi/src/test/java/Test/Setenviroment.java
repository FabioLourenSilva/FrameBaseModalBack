package Test;

import Core.BaseTest;

public class Setenviroment {
    private BaseTest baseTest;

    private String url;

    public String setEnviromentGetSingleUsers(){
        this.baseTest = new BaseTest();

        this.url = new StringBuilder()
                .append(this.baseTest.getSetupProperty("base-url"))
                .append(this.baseTest.getSetupProperty("endpoint.get"))
                .toString();
        return this.url;
    }

    public String setEnviromentPost(){
        this.baseTest = new BaseTest();

        this.url = new StringBuilder()
                .append(this.baseTest.getSetupProperty("base-url"))
                .append(this.baseTest.getSetupProperty("endpoint.post"))
                .toString();
        return this.url;
    }

    public String setEnviromenGetListUsers(String parametro){
        this.baseTest = new BaseTest();

        this.url = new StringBuilder()
                .append(this.baseTest.getSetupProperty("base-url"))
                .append(this.baseTest.getSetupProperty("endpoint.post" ) +parametro)
                .toString();
        return this.url;
    }
}
