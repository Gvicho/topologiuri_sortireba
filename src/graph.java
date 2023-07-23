import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class graph {
    public ArrayList<ArrayList<Integer>>  Graph ;
    public HashMap<String,Integer> name_id_map ;
    public HashMap<Integer,String> id_name_map ;

    private int Size;
    graph(){
        // constructor
        Size = 1;
        Graph = new ArrayList<ArrayList<Integer>>();
        Graph.add(new ArrayList<Integer>()); // I dont want to start from 0 so just add a free array in 0 index
        name_id_map = new HashMap<String,Integer>();
        id_name_map = new HashMap<Integer,String>();
    }
    public void add_Job_node(String Job_Name){
        // add a node in the graph
        Integer temp_var =  Integer.valueOf(Size);
        name_id_map.put(Job_Name,temp_var);
        id_name_map.put(temp_var,Job_Name);
        Graph.add(new ArrayList<Integer>());
        Size++;
    }

    public boolean set_node_as_prerequisite(String Job1,String Job2){
        //In order to do Job2 You must have done Job1.
        //იმისთვის რომ შეასრულო მე_2 საქმე, დამთავრებული უნდა გქონდეს პირველი საქმე.
        Integer Job1_id = name_id_map.get(Job1);
        Integer Job2_id = name_id_map.get(Job2);
        System.out.println(Job1_id + " was set as a prerequisite of " + Job2_id);
        if(Job1_id==0||Job2_id==0){
            //we didn't had such jobs
            //ანუ არ გვქონდა მსგავსი საქმე, ჩანაწერებში
            return false;
        }
        Graph.get(Job1_id).add(Job2_id);
        return true;
    }

    public void print_graph(){
        // for debugging purposes...
        System.out.println("Graph Size of : " + (Size-1));
        for(int i=1;i<Size;i++){
            System.out.print(i + " :  ");
            for(int j=0;j<Graph.get(i).size();j++){
                System.out.print(Graph.get(i).get(j) + " ");
            }
            System.out.print('\n');
        }
    }

    public void dfs(Integer node_id,Stack<Integer> st,int []node_state){
        // this is a standard topology sort dfs.1
        if(node_state[node_id] == 1){
            System.out.println("Cycle was found!!!!!");
            return;
        }
        if(node_state[node_id] == 2){
            return;
        }
        node_state[node_id] = 1;
        for(int i=0;i<Graph.get(node_id).size();i++){
            dfs(Graph.get(node_id).get(i),st,node_state);
        }
        node_state[node_id]=2;
        st.push(node_id);
    }

    public void print_topologt_sort(Stack<Integer> st){
        // we will print the nodes in the topologicly sorted order , after the sort is over
        while(!st.empty()){
            System.out.print((id_name_map.get(st.peek())) + " -> ");
            st.pop();
        }
        System.out.print('\n');
    }
    public void topological_sort(){

        Stack<Integer> st = new Stack<Integer>();
        int []node_state = new int[Size+1];
        for(Integer i=1;i<Size;i++){
            dfs(i,st,node_state);
        }
        print_topologt_sort(st);
    }


}
