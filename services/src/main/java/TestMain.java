import tools.ClientService;
import tools.InformationService;

/**
 * Created by valera on 5/3/17.
 */
public class TestMain {
    public static void main(String[] args){
        InformationService informationService = new InformationService();
        informationService.getFaculties().forEach(System.out::println);
        informationService.getRoles().forEach(System.out::println);
        informationService.getStatements().forEach(System.out::println);
        informationService.getSubjectNames().forEach(System.out::println);
        informationService.getSubjects().forEach(System.out::println);
        informationService.getUsers().forEach(System.out::println);

        ClientService clientService = new ClientService();
        System.out.println(clientService.register("valera", "valera", "valera"));
        System.out.println(clientService.login("valera", "valera"));
    }
}
