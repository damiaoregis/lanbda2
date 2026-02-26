import model.entities.Product;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    String path = "C:\\Users\\damia\\IdeaProjects\\lanbda2\\src\\in.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        List<Product> list = new ArrayList<>();

        String line = br.readLine();
        while (line != null) {
            String[] fields = line.split(",");
            list.add(new Product(fields[0], Double.parseDouble(fields[1])));
            line = br.readLine();
        }
        double avg = list.stream()
                .map(p-> p.getPrice())
                .reduce(0.0,(x,y)-> x + y)/ list.size();

        IO.println("Average price: "+ String.format("%.2f", avg));

        Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

        List<String> names = list.stream().filter(p -> p.getPrice() < avg )
                .map(p -> p.getName())
                .sorted(comp.reversed()).
                collect(Collectors.toList());
        names.forEach(IO::println);

    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

