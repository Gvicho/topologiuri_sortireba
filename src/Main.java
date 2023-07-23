import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //creating a scanner
        Scanner scanner = new Scanner(System.in);

        //standart input
        System.out.println("how many jobs are there?");
        int Job_quantity = scanner.nextInt();

        graph job_graph = new graph();
        scanner.nextLine();// flash to clear the last line

        //take the Job names , one name on each line
        for(int i=0;i<Job_quantity;i++){
            String Job_name = scanner.nextLine();
            job_graph.add_Job_node(Job_name);
        }

        //input a edges between a jobs (which job is a prerequisite of another job)
        System.out.println("how many prerequisits are there?");
        int Job_prerequisit_quantity = scanner.nextInt();
        scanner.nextLine();
        for(int i =0;i<Job_prerequisit_quantity;i++){
            //on each iteratien we input two differante lines.
            //on the first line there should be a prerequisite job
            //on the second line there should be a job which has a first job as a prerequisite.
            String Job_name1 = scanner.nextLine();
            String Job_name2 = scanner.nextLine();
            job_graph.set_node_as_prerequisite(Job_name1,Job_name2);
        }

        //just for debugging
        //job_graph.print_graph();

        //this will topologicly sort the graph of jobs, and print the correct order of jobs in which they should be done.
        job_graph.topological_sort();
    }
}


/*

simple example (for visual see the photos in the folder)

there are five jobs [ J1 , J2 , J3 , J4 , J5 ]
J1 is a prerequisite for J2 and J3 (You cant do them if you haven't done J1)
J3 is a prerequisite for J2
J2 is a prerequisite for J4
J4 is a prerequisite for J5

in which order should we do this jobs?

this problem can be converted in a directed acyclic graph problem.(if there is a cycle algorithm will detect it),
where jobs are the nodes and edges represent the prerequisit relationship between the jobs.
we will just topologicly sort the graph and the order of the nodes will be the answear.


test case for input:
5
J1
J2
J3
J4
J5
5
J1
J2
J3
J2
J2
J4
J4
J5


output will be :
J1 -> J3 -> J2 -> J4 -> J5 ->
 */