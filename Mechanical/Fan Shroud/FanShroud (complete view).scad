$fn=64;
//fan shutter system
difference(){
    //main cube bottom section
    union(){
        xdim2 = 80;
        ydim2 = 80;
        zdim2 = 15;
        translate([-40, -ydim2/2, 0])
        {
            cube([xdim2,ydim2,zdim2]);
        }
        translate([40, -3, 0])
        {
            cube([23, 25, 15]);
        }
    }
    
    xdim1 = 51;
    ydim1 = 51;
    zdim1 = 13;
    //center cut out
    translate([-xdim1/2, -ydim1/2, 0])
    {
        cube([xdim1,ydim1,zdim1]);
    }
    //top trim down
    translate([-40, -40, 8])
    {
        cube([69.5,82,12]);
    }
    //side cutout 1
    translate([-40, 29.5, 4])
    {
        cube([80,11.5,10]);
    }
    //side cutout 2
    translate([-40, -40, 4])
    {
        cube([80,10.5,10]);
    }
    //side cutout 3
    translate([-40, -40, 4])
    {
        cube([10.5,80,10]);
    }
    //side cutout 4a
    translate([29.5, -40, 4])
    {
        cube([10.5,37,16]);
    }
    //side cutout 4b
    translate([29.5, 22, 4])
    {
        cube([10.5,20,16]);
    }
    
    //servo cutouta
    translate([34.5, -3, 3])
    {
        cube([23,30,16]);
    }
    //servo cutoutb
    translate([29.5, -3, 3])
    {
        cube([36,12,16]);
    }
    //servo slot
    translate([29.5, 14.875, 3])
    {
        cube([35.5,2.75,16]);
    }
    //servo hole1
    rotate([90, 0, 0])
    translate([32.1,9,-30])
    {    
            cylinder(60, 1.3, 1.3);
        
    }
    //servo hole2
    rotate([90, 0, 0])
    translate([60,9,-30])
    {   
            cylinder(60, 1.3, 1.3);
        
    }
    
    
    radius = 2.1;
    //mount hole1
    translate([-33.5,33.5,0])
    {
        cylinder(5,radius,radius);
    }
    //countersink1
    translate([-33.5,33.5,3])
    {
        cylinder(3,radius,5);
    }
    //mount hole2
    translate([33.5,-33.5,0])
    {
        cylinder(5,radius,radius);
    }
    //countersink2
    translate([33.5,-33.5,3])
    {
        cylinder(3,radius,5);
    }
    //mount hole3
    translate([-33.5,-33.5,0])
    {
        cylinder(5,radius,radius);
    }
    //countersink3
    translate([-33.5,-33.5,3])
    {
        cylinder(3,radius,5);
    }
    //mount hole4
    translate([33.5,33.5,0])
    {
        cylinder(5,radius,radius);
    }
    //countersink4
    translate([33.5,33.5,3])
    {
        cylinder(3,radius,5);
    }
    
    //top shutter holes
    translate([22.5,0,8])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true); 
    }
    //top shutter indent
    translate([9.4,20,12])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);   
    }
    //middle shutter holes
    translate([6,0,8])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true);
        
    }
    //middle shutter indent
    translate([-7.1,20,12])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);      
    }
    //bottom shutter holes
    translate([-10.25,0,8])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true);
        
    }
    //bottom shutter indent
    translate([-23.4,20,12])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);
        
    }
    
    //pin indents in bottom section
    //pin 1
    translate([27.5,27.5,6])
    {
        cylinder(2.3, 1.1, 1.1); 
    }
    //pin 2
    translate([27.5,-27.5,6])
    {
        cylinder(2.3, 1.1, 1.1); 
    } 
    //pin 3
    translate([-27.5,27.5,6])
    {
        cylinder(2.3, 1.1, 1.1); 
    } 
    //pin 4
    translate([-27.5,-27.5,6])
    {
        cylinder(2.3, 1.1, 1.1); 
    } 
    
}

//top section with alignment pins
translate([0, 0, 8])
difference(){
    union(){
        xdim2 = 59;
        ydim2 = 59;
        zdim2 = 4;
        translate([-xdim2/2, -ydim2/2, 0])
        {
            cube([xdim2,ydim2,zdim2]);
        }
        //pin 1
        translate([27.5,27.5,-2])
        {
            cylinder(2, 1, 1); 
        }
        //pin 2
        translate([27.5,-27.5,-2])
        {
            cylinder(2, 1, 1); 
        } 
        //pin 3
        translate([-27.5,27.5,-2])
        {
            cylinder(2, 1, 1); 
        } 
        //pin 4
        translate([-27.5,-27.5,-2])
        {
            cylinder(2, 1, 1); 
        } 
    }

    xdim1 = 51;
    ydim1 = 51;
    zdim1 = 13;
    //center cut out
    translate([-xdim1/2, -ydim1/2, 0])
    {
        cube([xdim1,ydim1,zdim1]);
    }
    
        //top shutter holes
    translate([22.5,0,0])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true); 
    }
    //top shutter indent
    translate([9.4,20,4])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);   
    }
    //middle shutter holes
    translate([6,0,0])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true);
        
    }
    //middle shutter indent
    translate([-7.1,20,4])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);      
    }
    //bottom shutter holes
    translate([-10.25,0,0])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true);
        
    }
    //bottom shutter indent
    translate([-23.4,20,4])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);
        
    }
    
}

//top shutter
rotate([90, -10, 180])
union(){
    //Main cyl
    translate([-20.75, 11.75, 0])
    {
        cylinder(50, 2.5, 2.5, true);
    }
    //End pins
    translate([-20.75, 11.75, 0])
    {
        cylinder(60, 1.5, 1.5, true);
    }
    //Flap
    translate([-20.75, 11.25, -25])
    {
        cube([15, 3, 50]);
    }
    //Lever pin 1
    translate([-7.25, 12.75, 24])
    {
        cylinder(6.5, 1.5, 1.5);
    }
    //Lever pin 2
    translate([-7.25, 12.75, 29.5])
    {
        cylinder(6, 1, 1);
    }
}
//middle shutter
rotate([90, -10, 180])
union(){
    //Main cyl
    translate([-4.5, 9, 0])
    {
        cylinder(50, 2.5, 2.5, true);
    }
    //End pins
    translate([-4.5, 9, 0])
    {
        cylinder(60, 1.5, 1.5, true);
    }
    //Flap
    translate([-4.5, 8.5, -25])
    {
        cube([15, 3, 50]);
    }
    //Lever pin 1
    translate([9, 10, 24])
    {
        cylinder(6.5, 1.5, 1.5);
    }
    //Lever pin 2
    translate([9, 10, 29.5])
    {
        cylinder(6, 1, 1);
    }
}
//bottom shutter
rotate([90, -10, 180])
union(){
    //Main cyl
    translate([11.5, 6, 0])
    {
        cylinder(50, 2.5, 2.5, true);
    }
    //End pins
    translate([11.5, 6, 0])
    {
        cylinder(60, 1.5, 1.5, true);
    }
    //Flap
    translate([11.5, 5.5, -25])
    {
        cube([15, 3, 50]);
    }
    //Lever pin 1
    translate([25, 7, 24])
    {
        cylinder(6.5, 1.5, 1.5);
    }
    //Lever pin 2
    translate([25, 7, 29.5])
    {
        cylinder(6, 1, 1);
    }
}

//movement arm and pin holes
rotate([90,0,0])
translate([12.5,11.25,-34.25])
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

//SG90 servo placeholder
translate([46, 9.5, 9])
union(){
    //Main cube
    translate([0, 0, 0])
    {
        cube([22.5, 23.5, 12], true);
    }
    translate([-16.25, 5.5, -6])
    {
        cube([5, 2.5, 12]);
    }
    translate([11.25, 5.5, -6])
    {
        cube([5, 2.5, 12]);
    }
    rotate([-90, 0, 0])
    translate([-5.25, 0, 11.75])
    {
        cylinder(4.5, 6, 6);
    }
    rotate([0, 10, 0])
    translate([-19, 16.25, -1.5])
    {
        cube([14, 5, 1]);
    }
}
