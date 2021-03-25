package service;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class GUI extends Frame {
    public static GregorianCalendar currentDate;
    final static int CLOCKWIDTH = 605;
    final static int CLOCKHEIGHT = 200;
    public Owner owner;
    public Car car;
    public Service service=new Service();

    int i=0;

    JTextField price;
    JTextField firstName;
    JTextField lastName;
    JTextField age;
    JTextField address;
    JTextField CNP;
    JTextField phoneNumber;
    JFrame addCarFrame;
    JFrame endingFrame;
    JFrame stationFrame;
    JFrame waitindQFrame;
    JFrame diagnosFrame;
    JFrame statusFrame;
    JFrame payingFrame;
    JFrame principalFrame=new JFrame();
    JTextField model;
    JTextField manufacturer;
    JTextField year;
    JTextField licensePlate;
    JTextField diagnostic;
    JLabel dialog=new JLabel();

    JComboBox<Car> combobox;
    JRadioButton states1;
    JRadioButton states2;
    JTextField txt=new JTextField(10);


    GUI() {
        Car[] itemsArray = new Car[service.cars.repairStation.size()];
        itemsArray = service.cars.repairStation.toArray(itemsArray);
        combobox=new JComboBox<>(itemsArray);
        ButtonManager buttonManager=new ButtonManager();
        principalFrame.getContentPane().setBackground(Color.DARK_GRAY.darker());
        principalFrame.setLayout(new GridBagLayout());
        principalFrame.setTitle("Service");
        JButton addRepair=new JButton("addRepair");
        addRepair.setBackground(Color.lightGray);
        addRepair.setOpaque(true);
        JButton displayStations=new JButton("displayStation");
        displayStations.setBackground(Color.lightGray.brighter());
        displayStations.setOpaque(true);
        JButton displayQueue=new JButton("displayQueue");
        displayQueue.setBackground(Color.lightGray.brighter());
        displayQueue.setOpaque(true);
        JButton endRepair=new JButton("endRepair");
        endRepair.setBackground(Color.lightGray.brighter());
        endRepair.setOpaque(true);
        JButton diagnoseACar=new JButton("DiagnoseCar");
        diagnoseACar.setBackground(Color.lightGray.brighter());
        diagnoseACar.setOpaque(true);
        JButton changeStatus=new JButton("ChangeStatus");
        changeStatus.setBackground(Color.lightGray.brighter());
        changeStatus.setOpaque(true);
        int k=20-i;

        dialog.setFont(new Font("Serif", Font.PLAIN, 16));
        dialog.setText("There are " +k+" empty places");

        diagnoseACar.addActionListener(buttonManager);
        JLabel  yourClock=new JLabel();
        yourClock.setFont(new Font("Serif", Font.PLAIN, 16));
        ActionListener updateClockAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yourClock.setText(new Date().toString());
                yourClock.setForeground(Color.WHITE);
            }
        };
        Timer t = new Timer(1000, updateClockAction);
        t.start();
        JPanel h1=new JPanel();
        h1.setLayout(new GridLayout(3,0));
        JPanel h2=new JPanel();
        h2.setLayout(new GridLayout(3,0));
        endRepair.addActionListener(buttonManager);
        displayStations.addActionListener(buttonManager);
        addRepair.addActionListener(buttonManager);
        displayQueue.addActionListener(buttonManager);
        changeStatus.addActionListener(buttonManager);
        Box addi=Box.createVerticalBox();
        principalFrame.setSize(600,500);
        addi.add(addRepair);
        principalFrame.add(addi,addItem(0,0,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE));
        Box addi2=Box.createVerticalBox();
        addi2.add(yourClock);
        addi2.add(dialog);
        principalFrame.add(addi2,addItem(4,0,0,1,GridBagConstraints.WEST,GridBagConstraints.NONE));
        Box dis=Box.createVerticalBox();
        dis.add(displayStations);
        dis.add(displayQueue);
        principalFrame.add(dis,addItem(0,1,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE));
        Box re=Box.createVerticalBox();
        re.add(diagnoseACar);
        re.add(changeStatus);
        re.add(endRepair);
        principalFrame.add(re,addItem(0,3,1,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL));
        principalFrame.add(new JLabel(new ImageIcon("src/service/logo3 (1).png")),addItem(1,1,1,1,GridBagConstraints.EAST,GridBagConstraints.NONE));
        principalFrame.setSize(700, 500);
        dialog.setEnabled(false);
        principalFrame.setVisible(true);


    }
    private GridBagConstraints addItem( int x, int y, int width, int height, int align, int fill) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.insets = new Insets(5, 0, 5, 5);
        gc.anchor = align;
        gc.fill = fill;
        return gc;
    }

    public class ButtonManager implements ActionListener {
        Car aux;

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String action1 = actionEvent.getActionCommand();
            switch (action1) {
                case "endRepair": {
                    ending();
                    break;
                }
                case "Paid": {
                    paying();


                    break;

                }
                case "Call Owner": {
                    try {

                        aux = (Car) combobox.getSelectedItem();
                        Repairs rep = service.searchRepair(aux);
                        if (rep.getStatusOfReparation() == "InRepair") {
                            JOptionPane.showMessageDialog(null, rep.returnCar.callOwner());
                        } else {
                            String vorbe = "ERROR";
                            JOptionPane.showMessageDialog(null, vorbe);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String vorbe = "No cars";
                        JOptionPane.showMessageDialog(null, vorbe);
                    }

                    break;
                }
                case "DiagnoseCar": {
                    diagnose();
                    break;

                }
                case "Done": {
                    try {
                        aux = (Car) combobox.getSelectedItem();
                        Repairs rep = service.searchRepair(aux);
                        rep.setStatusOfReparation("diagnosed");
                        rep.setPrice(Double.parseDouble(price.getText().toString()));

                        if (price == null)
                            throw new Exception();
                        rep.setDiagnostic(diagnostic.getText().toString());
                        diagnosFrame.dispose();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Set price!");
                        e.printStackTrace();
                    }

                    break;

                }
                case "ChangeStatus": {
                    status();
                    break;
                }
                case "OK": {
                    try {
                        aux = (Car) combobox.getSelectedItem();
                        Repairs rep = service.searchRepair(aux);
                        if (states1.isSelected())
                            rep.setStatusOfReparation("InRepair");
                        else {
                            if (states2.isSelected()) {
                                rep.setStatusOfReparation("WaitingParts");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    statusFrame.dispose();
                    break;
                }
                case "displayStation": {
                    try {
                        station();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case "addRepair": {
                    try {
                        cars();

                    } catch (Exception e) {
                        String vorbes = "ERROR";
                        JOptionPane.showMessageDialog(null, vorbes);
                        e.printStackTrace();
                    }
                    break;
                }
                case "displayQueue": {
                    if (CarsOnYard.waitingQueue.size() == 0) {
                        String vorbe = "No cars waiting";
                        JOptionPane.showMessageDialog(null, vorbe);
                    } else {
                        try {
                            waitingQ();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case "add": {
                    try {
                        owner = new Owner(firstName.getText(), lastName.getText(), Integer.parseInt(age.getText()), address.getText(), CNP.getText(), Long.valueOf(phoneNumber.getText()));
                        car = new Car(owner, model.getText(), manufacturer.getText(), Integer.parseInt(year.getText()), licensePlate.getText());
                        addCarFrame.dispose();
                        i++;
                        dialog.setText("There are " +(20-i)+" empty places");
                        try {
                           Repairs repairsLocal;
                           repairsLocal=new Repairs(car,LocalDate.now());
                           repairsLocal.setStatusOfReparation("waiting_diagnose");
                            service.repairs.add(repairsLocal);

                           if (service.cars.isStationFree()==true) {
                               service.cars.addCarToStation(car);
                            } else {
                                service.cars.addCarToWaitingQueue(car);
                            }
                            car = null;
                            owner = null;
                        } catch (Exception e) {
                            String vorbes = "All station are full";
                            JOptionPane.showMessageDialog(null, vorbes);
                            e.printStackTrace();
                        }


                    } catch (Exception e) {
                        String vorbes = "Please make sure you have introduce all the fields correctly";
                        JOptionPane.showMessageDialog(null, vorbes);
                        e.printStackTrace();
                    }

                    break;
                }
                case "Ok": {
                    try {
                        i--;
                        dialog.setText("There are " + (20 - i) + " empty places");
                        aux = (Car) combobox.getSelectedItem();
                        Repairs rep = service.searchRepair(aux);
                        rep.setPrice(Double.parseDouble(txt.getText().toString()));
                        rep.setStatusOfReparation("ready");
                        service.cars.removeCarFromStation(aux);
                        if(service.cars.waitingQueue.size()!=0)
                        {
                            service.cars.addCarToStationFromWaitingQueue();
                        }
                        rep.setDateofExit(LocalDate.now());
                        Service.history(rep);
                        payingFrame.dispose();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        private void paying() {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    payingFrame = new JFrame();
                    payingFrame.setTitle("Paying");
                    payingFrame.setLayout(new GridBagLayout());
                    ButtonManager buttonManager = new ButtonManager();
                    JButton end = new JButton("Ok");
                    end.addActionListener(buttonManager);
                    JLabel prices = new JLabel("FinalPrice");
                    JLabel finaly = new JLabel();
                    try {

                        aux = (Car) combobox.getSelectedItem();
                        if (aux == null) {
                            String vorbe = "No car";
                            JOptionPane.showMessageDialog(null, vorbe);
                            payingFrame.setVisible(false);
                        } else {

                            Repairs rep = service.searchRepair(aux);
                            if (rep.getStatusOfReparation() == "InRepair") {
                                finaly.setText(String.valueOf(rep.getPrice()));
                                payingFrame.setVisible(true);
                                payingFrame.setSize(500, 150);

                            } else {
                                String vorbe = "ERROR";
                                JOptionPane.showMessageDialog(null, vorbe);
                                payingFrame.setVisible(false);


                            }

                        }


                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    payingFrame.add(new JLabel("InitialPrice"), addItem(0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    payingFrame.add(finaly, addItem(3, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    payingFrame.add(end, addItem(4, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    payingFrame.add(prices, addItem(0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    payingFrame.add(txt, addItem(2, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    Repairs rep = service.searchRepair(aux);
                }
            });
        }

        private void status() {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    statusFrame = new JFrame();
                    statusFrame.setTitle("ChangingStatus");
                    statusFrame.setLayout(new GridBagLayout());
                    ButtonManager buttonManager = new ButtonManager();

                    Car[] itemsArray = new Car[service.cars.repairStation.size()];
                    itemsArray = service.cars.repairStation.toArray(itemsArray);
                    combobox=new JComboBox<>(itemsArray);
                    states1 = new JRadioButton("InRepair");
                    states2 = new JRadioButton("WaintinParts");
                    ButtonGroup group = new ButtonGroup();
                    group.add(states1);
                    group.add(states2);
                    statusFrame.add(combobox, addItem(0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    JButton end = new JButton("OK");
                    end.addActionListener(buttonManager);
                    statusFrame.add(states1, addItem(3, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    statusFrame.add(states2, addItem(4, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    try {
                        aux = (Car) combobox.getSelectedItem();
                        Repairs rep = service.searchRepair(aux);
                        if (states1.isSelected())
                            rep.setStatusOfReparation("InRepair");
                        else {
                            if (states2.isSelected()) {
                                rep.setStatusOfReparation("WaitingParts");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String vorbe = "No cars";
                        JOptionPane.showMessageDialog(null, vorbe);
                    }

                    statusFrame.add(end, addItem(2, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                    if (aux != null) {
                        statusFrame.setVisible(true);
                        statusFrame.setSize(500, 150);
                    }

                }
            });
        }

        private void diagnose() {
            EventQueue.invokeLater(() -> {
                diagnosFrame = new JFrame();
                diagnosFrame.setLayout(new GridBagLayout());
                ButtonManager buttonManager = new ButtonManager();
                Car[] itemsArray = new Car[service.cars.repairStation.size()];
                itemsArray = service.cars.repairStation.toArray(itemsArray);
                combobox=new JComboBox<>(itemsArray);
                JLabel diag = new JLabel("Diagnostic");
                diagnostic = new JTextField(20);
                diagnosFrame.add(combobox, addItem(0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                diagnosFrame.add(diagnostic, addItem(1, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                diagnosFrame.add(diag, addItem(0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                JButton end = new JButton("Done");
                end.addActionListener(buttonManager);
                JLabel ajut = new JLabel("Price:");
                diagnosFrame.add(ajut, addItem(2, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                price = new JTextField(10);
                diagnosFrame.add(price, addItem(3, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                aux = (Car) combobox.getSelectedItem();
                diagnosFrame.add(end, addItem(3, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                diagnosFrame.setVisible(true);
                diagnosFrame.setSize(500, 150);
            });
        }

        private void station() {
            EventQueue.invokeLater(() -> {
                stationFrame = new JFrame();
                stationFrame.setLayout(new GridLayout(8, 0));
                stationFrame.setBounds(30, 30, 800, 600);
                stationFrame.setTitle("Stations");
                JPanel f = new JPanel();
                f.setLayout(new GridLayout(0, 1));
                int i=0;
                for(Car cars:CarsOnYard.repairStation)
                {
                    if(cars!=null)
                    {
                        Repairs rep;
                        rep=service.searchRepair(cars);
                        JTextArea ta=new JTextArea(300,300);
                        ta.append(cars.getLicensePlate()+ " "+rep.getStatusOfReparation()+" "+rep.getDiagnostic()+" "+rep.getPrice());
                        ta.setEditable(false);
                        f.add(ta);

                    }
                }
                stationFrame.setVisible(true);
                stationFrame.add(f);
           });
        }

        private void waitingQ() {
            EventQueue.invokeLater(() -> {
                waitindQFrame = new JFrame();
                waitindQFrame.setLayout(new GridLayout(8, 0));
                waitindQFrame.setBounds(30, 30, 50, 240);
                waitindQFrame.setTitle("WaitingsCar");
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (IllegalAccessException | ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                JPanel f = new JPanel();
                f.setLayout(new GridLayout(0, 1));
                JLabel la;
                for (Car value : CarsOnYard.waitingQueue) {
                    la = new JLabel(value.toString());
                    f.add(la);
                }
                waitindQFrame.setVisible(true);
                waitindQFrame.add(f);
            });
        }

        private void cars() {
            EventQueue.invokeLater((() -> {
                addCarFrame = new JFrame();
                addCarFrame.setLayout(new GridBagLayout());
                addCarFrame.setTitle("Cars");
                ButtonManager  buttonManager1= new ButtonManager();
                JButton add = new JButton("add");
                add.addActionListener(buttonManager1);
                JLabel b1 = new JLabel("Model");
                model = new JTextField(20);
                JLabel b2 = new JLabel("Manufacturer");
                manufacturer = new JTextField(20);
                JLabel b3 = new JLabel("Year");
                year = new JTextField(4);
                JLabel b4 = new JLabel("License Plate");
                licensePlate = new JTextField(10);
                addCarFrame.add(new JLabel("<html> <font color='red'>Details about car:</font></html>"), addItem(0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(b1, addItem(0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(b2, addItem(1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(b3, addItem(2, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(b4, addItem(3, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(model, addItem(0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(manufacturer, addItem(1, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(year, addItem(2, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(licensePlate, addItem(3, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(add, addItem(3, 11, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.setSize(800, 500);
                addCarFrame.setVisible(true);
                Box box = Box.createVerticalBox();
                JLabel a1 = new JLabel("First Name");
                firstName = new JTextField(20);
                JLabel a2 = new JLabel("Last Name");
                lastName = new JTextField(20);
                JLabel a3 = new JLabel("Age");
                age = new JTextField(3);
                JLabel a4 = new JLabel("Address");
                address = new JTextField(20);
                JLabel a5 = new JLabel("CNP");
                CNP = new JTextField(13);
                JLabel a6 = new JLabel("PhoneNumber");
                phoneNumber = new JTextField(10);
                addCarFrame.add(new JLabel("<html> <font color='red'>Details about owner:</font></html>"), addItem(0, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(a1, addItem(0, 6, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(a2, addItem(0, 7, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(a3, addItem(0, 8, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(a4, addItem(0, 9, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(a5, addItem(0, 10, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(a6, addItem(0, 11, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(firstName, addItem(1, 6, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(lastName, addItem(1, 7, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(age, addItem(1, 8, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(address, addItem(1, 9, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(CNP, addItem(1, 10, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(phoneNumber, addItem(1, 11, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                addCarFrame.add(box, addItem(1, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
            }));


        }

        public void ending() {
            EventQueue.invokeLater(() -> {
                endingFrame = new JFrame();
                endingFrame.setTitle("Ending");
                endingFrame.setLayout(new GridBagLayout());
                ButtonManager buttonManager = new ButtonManager();
                JButton paid = new JButton("Paid");
                paid.addActionListener(buttonManager);
                endingFrame.add(paid, addItem(2, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                Car[] itemsArray = new Car[service.cars.repairStation.size()];
                itemsArray = service.cars.repairStation.toArray(itemsArray);
                combobox=new JComboBox<>(itemsArray);
                endingFrame.add(combobox, addItem(0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                JButton end = new JButton("Call Owner");
                end.addActionListener(buttonManager);
                endingFrame.add(end, addItem(4, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE));
                endingFrame.setVisible(true);
                endingFrame.setSize(500, 65);
            });
        }

    }

}