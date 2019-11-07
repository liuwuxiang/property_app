package com.springmvc.entity;

public class UserSecurityQuestion {
    private Integer id;
    private Integer one_security_question_id;
    private String one_security_question_answer;
    private Integer two_security_question_id;
    private String two_security_question_answer;
    private Integer three_security_question_id;
    private String three_security_question_answer;
    private Integer user_id;


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOne_security_question_id() {
        return one_security_question_id;
    }

    public void setOne_security_question_id(Integer one_security_question_id) {
        this.one_security_question_id = one_security_question_id;
    }

    public String getOne_security_question_answer() {
        return one_security_question_answer;
    }

    public void setOne_security_question_answer(String one_security_question_answer) {
        this.one_security_question_answer = one_security_question_answer;
    }

    public Integer getTwo_security_question_id() {
        return two_security_question_id;
    }

    public void setTwo_security_question_id(Integer two_security_question_id) {
        this.two_security_question_id = two_security_question_id;
    }

    public String getTwo_security_question_answer() {
        return two_security_question_answer;
    }

    public void setTwo_security_question_answer(String two_security_question_answer) {
        this.two_security_question_answer = two_security_question_answer;
    }

    public Integer getThree_security_question_id() {
        return three_security_question_id;
    }

    public void setThree_security_question_id(Integer three_security_question_id) {
        this.three_security_question_id = three_security_question_id;
    }

    public String getThree_security_question_answer() {
        return three_security_question_answer;
    }

    public void setThree_security_question_answer(String three_security_question_answer) {
        this.three_security_question_answer = three_security_question_answer;
    }
}
