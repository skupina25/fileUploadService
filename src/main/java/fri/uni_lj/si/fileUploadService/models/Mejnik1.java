package fri.uni_lj.si.fileUploadService.models;

import java.util.ArrayList;

public class Mejnik1 {
    private final ArrayList<String> clani;
    private final String opis_projekta;
    private final ArrayList<String> mikrostoritve;
    private final ArrayList<String> github;
    private final ArrayList<String> travis;
    private final ArrayList<String> dockerhub;

    public Mejnik1() {
        ArrayList<String> cl = new ArrayList<>();
        cl.add("zp8358");
        cl.add("zk5343");
        this.clani = cl;

        this.opis_projekta = "Najin projekt implementira aplikacijo za task handling (podobno kot jira).";

        ArrayList<String> ms = new ArrayList<>();
        ms.add("http://20.73.137.91:8080/v1/files");
        ms.add("http://1.2.3.4/v1/tasks");
        this.mikrostoritve = ms;

        ArrayList<String> gh = new ArrayList<>();
        gh.add("https://github.com/skupina25/fileUploadService");
        gh.add("https://github.com/skupina25/taskManagementService");
        this.github = gh;

        ArrayList<String> ts = new ArrayList<>();
        ts.add("https://travis-ci.com/github/skupina25/fileUploadService");
        ts.add("https://travis-ci.com/github/skupina25/taskManagementService");
        this.travis = ts;

        ArrayList<String> dh = new ArrayList<>();
        dh.add("https://hub.docker.com/r/zp8358/fileuploadservice");
        dh.add("https://hub.docker.com/r/zigakleine/task-management-service");
        this.dockerhub = dh;
    }

    public ArrayList<String> getClani() {
        return clani;
    }

    public String getOpis_projekta() {
        return opis_projekta;
    }

    public ArrayList<String> getMikrostoritve() {
        return mikrostoritve;
    }

    public ArrayList<String> getGithub() {
        return github;
    }

    public ArrayList<String> getTravis() {
        return travis;
    }

    public ArrayList<String> getDockerhub() {
        return dockerhub;
    }
}
