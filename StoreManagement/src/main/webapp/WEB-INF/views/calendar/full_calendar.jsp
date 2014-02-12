<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<t:template>
	<jsp:body>
	  <div class="container">
	    <div class="row">
	      <div class="area-top clearfix">
	        <div class="pull-left header">
	          <h3 class="title">
	            <i class="icon-calendar"></i>
	            Full Calendar
	          </h3>
	          <h5>
	            <span>
	              A subtitle can go here
	            </span>
	          </h5>
	        </div>
	      </div>
	    </div>
	  </div>

  <div class="container padded">
    <div class="row">

      <!-- Breadcrumb line -->

      <div id="breadcrumbs">
        <div class="breadcrumb-button blue">
          <span class="breadcrumb-label"><i class="icon-home"></i> Home</span>
          <span class="breadcrumb-arrow"><span></span></span>
        </div>

        <div class="breadcrumb-button">
          <span class="breadcrumb-label">
            <i class="icon-calendar"></i> Full Calendar
          </span>
          <span class="breadcrumb-arrow"><span></span></span>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
  <div class="col-md-3" id='external-events'>
    <div class="box">
      <div class="box-header">
							<span class="title">Draggable events</span>
						</div>
      <div class="box-content">
        <div class="nav-menu">
          <ul class="nav nav-list">
            
                <li><a class='external-event'>My Event 1</a></li>
            
                <li><a class='external-event'>My Event 2</a></li>
            
                <li><a class='external-event'>My Event 3</a></li>
            
                <li><a class='external-event'>My Event 4</a></li>
            
                <li><a class='external-event'>My Event 5</a></li>
            
            <li class="hidden-option">
              <a id="add-event" class="flat" href="#"><i
											class="icon-plus"></i>Create event</a>
            </li>
            <li style="padding: 10px">
              <div class="clearfix">
                <div class="pull-left">
                  remove after drop
                </div>
                <div class="pull-right" style="margin-right: 2px;">
                  <input type='checkbox' id='drop-remove' class="checky" />
                  <label for='drop-remove' class="checky"><span></span></label>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>

  <div class="col-md-9">
    <div class="box">
      <div class="box-header">
							<span class="title">Full calendar</span>
						</div>
      <div class="box-content">
        <div id="calendar"></div>
      </div>
    </div>
  </div>
</div>
</div>
</jsp:body>
</t:template>