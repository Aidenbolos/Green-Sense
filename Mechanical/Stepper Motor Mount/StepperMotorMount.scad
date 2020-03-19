$fn=64;
//28BYJ-48 stepper Motor Case (Main housing)
difference(){
    union(){
         //main body cylinder
         translate([0, 0, 0])
         {
             cylinder(24.25, 22, 22); 
         }
         //main body cube
         translate([-22, -45, 0])
         {
             cube([44, 45, 24.25]); 
         }      
    }
    
    //stepper motor hole
    translate([0, 0, 4.75])
    {
        cylinder(20, 14.25, 14.25);
    }
    
    //cube cutout for stepper bolts
    translate([-21.25, -3.75, 23.35])
    {
        cube([42.5, 7.5, 1]);
    }
    
    //stepper bolt hole Q1
    translate([17.5, 0, 0])
    {
        cylinder(25, 2.1, 2.1);
    }
    
    //stepper bolt hole Q4
    translate([-17.5, 0, 0])
    {
        cylinder(25, 2.1, 2.1);
    }
    
    //case center hole
    translate([0, -22, 0])
    {
        cylinder(25, 2.05, 2.05);
    }
    
    //alignment hole Q1
    translate([16, -12, 19])
    {
        cylinder(25, 1.55, 1.55);
    }
    
    //alignment hole Q4
    translate([-16, -12, 19])
    {
        cylinder(25, 1.55, 1.55);
    }
    
    //stepper bottom cutout/wire channel
    translate([-9, -17, 4.75])
    {
        cube([18, 6, 20]);
    }
    
    //driver board underneath square
    translate([-15, -42, 3])
    {
        cube([30, 7, 21.25]);
    }
    
    //driver board above square
    translate([-20, -35, 3])
    {
        cube([40, 18, 21.25]);
    }
    
    //driver board slot
    translate([-17.75, -39, 3])
    {
        cube([35.5, 2, 21.25]);
    }
    
    //wire slot/hole
    translate([-22, -27, 18.25])
    {
        cube([4, 6, 6]);
    }
}

//28BYJ-48 stepper Motor Case (Housing cover)
translate([-60, 0, 0])
difference(){
    union(){
        //secondary body cylinder
        translate([0, 0, 0])
        {
            cylinder(14, 22, 22); 
        }
        //secondary body cube
        translate([-22, -45, 0])
        {
            cube([44, 45, 14]); 
        }
        //alignment pin Q1
        translate([16, -12, 0])
        {
            cylinder(18, 1.5, 1.5);
        }
        //alignment pin Q4
        translate([-16, -12, 0])
        {
            cylinder(18, 1.5, 1.5);
        }     
    }
    
    //sprocket area cylinder hollow
    translate([0, 10, 0])
    {
        cylinder(15, 12.5, 12.5);
    }
    
    //sprocket area cube hollow
    translate([-12.5, 10, 0])
    {
        cube([25, 13, 15]);
    }
    
    //stepper bolt hole Q1
    translate([17.5, 0, 0])
    {
        cylinder(25, 2.1, 2.1);
    }
    
    //stepper bolt hole Q4
    translate([-17.5, 0, 0])
    {
        cylinder(25, 2.1, 2.1);
    }
    
    //case center hole
    translate([0, -22, 0])
    {
        cylinder(15, 2.05, 2.05);
    }
    
    //driver board underneath square
    translate([-15, -42, 3])
    {
        cube([30, 7, 21.25]);
    }
    
    //driver board above square
    translate([-20, -35, 3])
    {
        cube([40, 18, 21.25]);
    }
    
    //driver board slot
    translate([-17.75, -39, 3])
    {
        cube([35.5, 2, 21.25]);
    }
}