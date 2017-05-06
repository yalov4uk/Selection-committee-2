import beans.*;
import dao.FacultyDao;
import interfaces.ICRUDService;
import interfaces.IEnrolleeService;
import tools.CRUDService;
import tools.EnrolleeService;

import javax.jws.soap.SOAPBinding;

/**
 * Created by valera on 5/3/17.
 */
public class TestMain {
    public static void main(String[] args){

        /*IClientService clientService = new ClientService();
        System.out.println(clientService.register("valera1", "valera1", "valera1"));
        System.out.println(clientService.login("valera", "valera"));*/

        ICRUDService crudService = new CRUDService();

        //crudService.getAll(Faculty.class).forEach(System.out::println);
        //crudService.getAll(Role.class).forEach(System.out::println);
        //crudService.getAll(Statement.class).forEach(System.out::println);
        crudService.getAll(Subject.class).forEach(System.out::println);
        //crudService.getAll(SubjectName.class).forEach(System.out::println);
        //crudService.getAll(User.class).forEach(System.out::println);



        /*User user = crudService.read(2, User.class);
        IEnrolleeService enrolleeService = new EnrolleeService();
        enrolleeService.getRequiredSubjectNames(user,
        new FacultyDao(Faculty.class).findByName("FKSiS")).forEach(System.out::println);/*

        /*User user = new User("1", "1", "1");
        user.setRole(crudService.read(1, Role.class));
        System.out.println(crudService.create(user));
        user.setName("2");
        System.out.println(crudService.update(user));
        System.out.println(crudService.delete(user));*/
    }
}
