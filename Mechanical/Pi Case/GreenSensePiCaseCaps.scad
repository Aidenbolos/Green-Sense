$fn=64;
//Pi Case Caps
difference(){
    union(){
        //Cap 1
        translate([10, -34, 0])
        {
            cube([5, 68, 5]);
        }
        
        translate([-10, -34, 0])
        { 
            cube([5 , 68, 5]);
        }
        
        //alightment pins
        radius1 = 1;
        //pin in Q4
        translate([-7.5,20,-4])
        {
            cylinder (6,radius1,radius1);
        }
        //pin in Q3
        translate([-7.5,-20,-4])
        {
            cylinder (6,radius1,radius1);
        }
        //pin in Q2
        translate([12.5,-20,-4])
        {
            cylinder (6,radius1,radius1);
        }
        //pin in Q1
        translate([12.5,20,-4])
        {
            cylinder (6,radius1,radius1);
        }
   }

    //screw holes
    zdim1 = 42;
    radius = 1.55;
    //hole in Q4
    translate([-7.5, 25, 0])
    {
        cylinder (zdim1,radius,radius);
    }
    //hole in Q3
    translate([-7.5, -25, 0])
    {
        cylinder (zdim1,radius,radius);
    }
    //hole in Q2
    translate([12.5, -25, 0])
    {
        cylinder (zdim1,radius,radius);
    }
    //hole in Q1
    translate([12.5, 25, 0])
    {
        cylinder (zdim1,radius,radius);
    }
    
    //hinge for acrylic door
    rotate([0, 90, 0])
    translate([-1.75, 30, -60])
    {
        cylinder(120, 1.55, 1.55);
    }
    //slot for hinge
    translate([-60, 28.45, 0])
    {
        cube([120, 3.1, 1.75]);
    }
}