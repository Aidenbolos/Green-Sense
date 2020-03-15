$fn=64;
//Shutter blade 1
rotate([90, 180, 0])
union(){
    //Main cyl
    translate([0, 0, 0])
    {
        cylinder(50, 2.5, 2.5);
    }
    //Rotation pins
    translate([0, 0, -5])
    {
        cylinder(60, 1.5, 1.5);
    }
    //Flap
    translate([0, -0.5, 0])
    {
        cube([15, 3, 50]);
    }
    //Lever pin 1
    translate([13.5, 1, 49])
    {
        cylinder(6.5, 1.5, 1.5);
    }
    //Lever pin 2
    translate([13.5, 1, 54.5])
    {
        cylinder(7, 1, 1);
    }
}

//Shutter blade 2
rotate([90, 180, 0])
translate([25, 0, 0])
union(){
    //Main cyl
    translate([0, 0, 0])
    {
        cylinder(50, 2.5, 2.5);
    }
    //Rotation pins
    translate([0, 0, -5])
    {
        cylinder(60, 1.5, 1.5);
    }
    //Flap
    translate([0, -0.5, 0])
    {
        cube([15, 3, 50]);
    }
    //Lever pin 1
    translate([13.5, 1, 49])
    {
        cylinder(6.5, 1.5, 1.5);
    }
    //Lever pin 2
    translate([13.5, 1, 54.5])
    {
        cylinder(7, 1, 1);
    }
}
//Shutter blade 3
rotate([90, 180, 0])
translate([-25, 0, 0])
union(){
    //Main cyl
    translate([0, 0, 0])
    {
        cylinder(50, 2.5, 2.5);
    }
    //Rotation pins
    translate([0, 0, -5])
    {
        cylinder(60, 1.5, 1.5);
    }
    //Flap
    translate([0, -0.5, 0])
    {
        cube([15, 3, 50]);
    }
    //Lever pin 1
    translate([13.5, 1, 49])
    {
        cylinder(6.5, 1.5, 1.5);
    }
    //Lever pin 2
    translate([13.5, 1, 54.5])
    {
        cylinder(7, 1, 1);
    }
}

//Shutter movement arm
rotate([0, 0, 90])
translate([-23, 50, -2.5]) 
difference(){
    {
        //Main cube
        translate([-40, -4, 0])
        {
            cube([63, 8, 3.5]);
        }
    }
    translate([-36, 0, -1])
    {
        cylinder(5, 1.25, 1.25);
    }
    
    translate([-19.5, 0, -1])
    {
        cylinder(5, 1.25, 1.25);
    }
    
    translate([-3, 0, -1])
    {
        cylinder(5, 1.25, 1.25);
    }
    
    translate([12, 0, -1])
    {
        cylinder(5, 0.75, 0.75);
    }
    
    translate([14.5, 0, -1])
    {
        cylinder(5, 0.75, 0.75);
    }
    
    translate([17, 0, -1])
    {
        cylinder(5, 0.75, 0.75);
    }
    
    translate([19.5, 0, -1])
    {
        cylinder(5, 0.75, 0.75);
    }
 
}
