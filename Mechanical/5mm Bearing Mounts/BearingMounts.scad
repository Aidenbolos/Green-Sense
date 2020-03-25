$fn=64;
//90 degree 5mm bearing mounts
//Mount 1
difference(){
    union(){
        //bearing housing cylinder
        translate([0, 0, 0])
        {
            cylinder(7, 11, 11);
        }
        //cube connecting cylinder to mount    
        translate([-9, 0, 0])
        {
            cube([18, 15, 7]);
        }   
        //cube for mount plate
        translate([-17, 15, 0])
        {
            cube([34, 4, 9]);
        }  
        
    }
    //hollow for bearing
    translate([0, 0, 2])
    {
        cylinder(5, 8, 8);
    }
    //indent for inner bearing clearance    
    translate([0, 0, 1.5])
    {
        cylinder(5, 4.5, 4.5);
    }
    //mount hole 1
    rotate([90, 0, 0])
    translate([-13, 4.5, -20])
    {
        cylinder(5, 1.5, 1.5);
    }
    //mount hole 2
    rotate([90, 0, 0])
    translate([13, 4.5, -20])
    {
        cylinder(5, 1.5, 1.5);
    } 
}

//Mount 2
translate([40, 0, 0])
difference(){
    union(){
        //bearing housing cylinder
        translate([0, 0, 0])
        {
            cylinder(7, 11, 11);
        }
        //cube connecting cylinder to mount    
        translate([-9, 0, 0])
        {
            cube([18, 15, 7]);
        }   
        //cube for mount plate
        translate([-17, 15, 0])
        {
            cube([34, 4, 9]);
        }  
        
    }
    //hollow for bearing
    translate([0, 0, 2])
    {
        cylinder(5, 8, 8);
    }
    //indent for inner bearing clearance    
    translate([0, 0, 1.5])
    {
        cylinder(5, 4.5, 4.5);
    }
    //mount hole 1
    rotate([90, 0, 0])
    translate([-13, 4.5, -20])
    {
        cylinder(5, 1.5, 1.5);
    }
    //mount hole 2
    rotate([90, 0, 0])
    translate([13, 4.5, -20])
    {
        cylinder(5, 1.5, 1.5);
    } 
}

//Mount 3
translate([0, 35, 0])
difference(){
    union(){
        //bearing housing cylinder
        translate([0, 0, 0])
        {
            cylinder(7, 11, 11);
        }
        //cube connecting cylinder to mount    
        translate([-9, 0, 0])
        {
            cube([18, 15, 7]);
        }   
        //cube for mount plate
        translate([-17, 15, 0])
        {
            cube([34, 4, 9]);
        }  
        
    }
    //hollow for bearing
    translate([0, 0, 2])
    {
        cylinder(5, 8, 8);
    }
    //indent for inner bearing clearance    
    translate([0, 0, 1.5])
    {
        cylinder(5, 4.5, 4.5);
    }
    //mount hole 1
    rotate([90, 0, 0])
    translate([-13, 4.5, -20])
    {
        cylinder(5, 1.5, 1.5);
    }
    //mount hole 2
    rotate([90, 0, 0])
    translate([13, 4.5, -20])
    {
        cylinder(5, 1.5, 1.5);
    } 
}

//Mount 4
translate([40, 35, 0])
difference(){
    union(){
        //bearing housing cylinder
        translate([0, 0, 0])
        {
            cylinder(7, 11, 11);
        }
        //cube connecting cylinder to mount    
        translate([-9, 0, 0])
        {
            cube([18, 15, 7]);
        }   
        //cube for mount plate
        translate([-17, 15, 0])
        {
            cube([34, 4, 9]);
        }  
        
    }
    //hollow for bearing
    translate([0, 0, 2])
    {
        cylinder(5, 8, 8);
    }
    //indent for inner bearing clearance    
    translate([0, 0, 1.5])
    {
        cylinder(5, 4.5, 4.5);
    }
    //mount hole 1
    rotate([90, 0, 0])
    translate([-13, 4.5, -20])
    {
        cylinder(5, 1.5, 1.5);
    }
    //mount hole 2
    rotate([90, 0, 0])
    translate([13, 4.5, -20])
    {
        cylinder(5, 1.5, 1.5);
    } 
}
