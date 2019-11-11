/* score html5 commJs */
var icScore = function(sBox, curr, sNode) {
    var _sBox = $(sBox),
    	_sbxW = parseInt(_sBox.width()),
        _sLi = _sBox.children(sNode),
        _siW = parseInt(_sLi.eq(0).width()),
        pageX,
        _index,
        i = iScore = iStart = 0;
    _sBox.on('touchstart', function(e) {
    	var touche = e.touches[0];
    	pageX = touche.pageX;
    });
    _sBox.on('touchmove', sNode, function(e) {
    	var touche = e.touches[0],
    		X = parseInt(touche.pageX - pageX);
    	if (X<0) {
    		X = parseInt(pageX - touche.pageX);
    		return false;
    	}
    	if(X > _sbxW) {
    		X = _sbxW;
    		return false;
    	}
    	_index = (parseInt(X/_siW)+1);
    	curPoint(_index);
    	return false;
    });
    _sBox.on('touchend', function(e) {
    	return false;
    });
    _sBox.on('touchcancel', function(e) {
    	return false;
    });
    function curPoint(cNum) {
    	if (cNum) {
    		iScore = cNum;
    	} else {
    		iScore = iStart;
    	}
    	for (i=0; i<_sLi.length; i++) {
    		if (i<iScore) {
    			_sLi.eq(i).addClass('curr');
    		} else {
    			_sLi.eq(i).removeClass('curr');
    		}
    	}
    }
};

