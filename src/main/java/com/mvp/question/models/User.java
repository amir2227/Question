package com.mvp.question.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class)
})
@JsonIgnoreProperties({ "password", "questions", "answers", "my_answers", "wallets", "transactions" })
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(length = 20)
    private String username;

    @Size(max = 30)
    @Column(length = 30)
    private String fullname;

    @Size(max = 30)
    @Column(length = 30)
    private String email;

    @Size(max = 11)
    @Column(length = 11)
    private String phone;

    @Type(type = "string-array")
    @Column(nullable = false, columnDefinition = "text[]")
    private String[] talents;

    @NotBlank
    @Size(max = 120)
    @Column(length = 120)
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Question> questions;
    @OneToMany(mappedBy = "answer_owner")
    private List<Answer> answers;
    @OneToMany(mappedBy = "answerer")
    private List<Answer> my_answers;
    @OneToMany(mappedBy = "user")
    private List<Wallet> wallets;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(@NotBlank @Size(max = 20) String username, @Size(max = 30) String fullname,
            @Size(max = 30) String email, @NotBlank @Size(max = 11) String phone) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getTalents() {
        return talents;
    }

    public void setTalents(String[] talents) {
        this.talents = talents;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getMy_answers() {
        return my_answers;
    }

    public void setMy_answers(List<Answer> my_answers) {
        this.my_answers = my_answers;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}