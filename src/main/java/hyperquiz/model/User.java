package hyperquiz.model;

import com.fasterxml.jackson.annotation.JsonView;
import hyperquiz.view.View;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User extends AbstractEntity<Long,User> {
    @JsonView({View.UserView.External.class,View.QuizView.External.class})
    @Column(name="username",nullable = false,unique = true)
    @Size(min=2,max=15,message="Username must be between 2 and 15 characters")
    private  String username;

    @JsonView({View.UserView.External.class})
    @Column(nullable = false,unique = true)
    @Email(message = "Email not valid")
    private String email;
    @JsonView(View.UserView.Internal.class)
    @Column(nullable = false)
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+,.\\\\\\/;':\"-]).{8,}$")
    @Size(min=8,max=30,message="Password must be between 8 and 15 characters")
    private String password;

    @JsonView(View.UserView.External.class)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Gender gender;

    @JsonView(View.UserView.External.class)
    @Enumerated(EnumType.ORDINAL)
    private Role role=Role.PLAYER;

    @JsonView(View.UserView.External.class)
    @Column
    private URL userPicture;

    @JsonView(View.UserView.External.class)
    @Column
    @Size(min=20,max=300,message="Password must be between 8 and 15 characters")
    private String description;

    @JsonView(View.UserView.External.class)
    @Column
    @Size(max=512)
    private String metadata;

    @JsonView(View.UserView.Internal.class)
    @Column
    private boolean status;

    @JsonView(View.UserView.Internal.class)
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "author")
    private List<Quiz> quizzes=new ArrayList<>();

    public User() {
        this.quizzes=new ArrayList<>();
        username = null;
    }

    public User(Long id) {
        super(id);
    }

    public User(String username, String email, String password, Gender gender, boolean status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.status = status;
    }

    public User(String username, String email, String password, Gender gender, Role role, URL userPicture, String description, String metadata, boolean status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.userPicture = userPicture;
        this.description = description;
        this.metadata = metadata;
        this.status = status;
    }

    public User(Long id, String username, String email, String password, Gender gender, Role role, URL userPicture, String description, String metadata, boolean status) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.userPicture = userPicture;
        this.description = description;
        this.metadata = metadata;
        this.status = status;
        this.quizzes = new ArrayList<>();
    }

    public User(Long id, String username, String email, String password, URL userPicture, String description, String metadata) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.userPicture = userPicture;
        this.description = description;
        this.metadata = metadata;
    }

    public User(String username, String email, String password, @NonNull Gender gender, Role role, String description, String metadata, boolean status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.description = description;
        this.metadata = metadata;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public URL getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(URL userPicture) {
        this.userPicture = userPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.username);
        return sb.toString();
    }

    public String forDB() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", role=").append(role);
        sb.append(", userPicture=").append(userPicture);
        sb.append(", description='").append(description).append('\'');
        sb.append(", metadata='").append(metadata).append('\'');
        sb.append(", status=").append(status);
        sb.append(", quizzes=").append(quizzes);
        sb.append('}');
        return sb.toString();
    }
}
