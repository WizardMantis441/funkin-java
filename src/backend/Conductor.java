package src.backend;

public class Conductor {
    public static Double bpm = 100.0;
    // public static Int[] bpmChangeMap = {};

    public static Double crochet = ((60 / bpm) * 1000); // beats in ms
    public static Double stepCrochet = crochet / 4; // beats in ms

    public static Double songPosition = 0.0;

    public static int curStep = 0;
    public static int curBeat = 0;
    public static int curMeasure = 0;

    public Double getBpm() {
        return bpm;
    }

    public void setBPM(double newBPM) {
        bpm = newBPM;
        crochet = ((60 / bpm) * 1000);
        stepCrochet = crochet / 4;
    }
}

/*
# input stuff
var hitWindow:float = 250
var latePressWindow:float = 1
var earlyPressWindow:float = 0.5

signal stepHit(curStep:int);
signal beatHit(curBeat:int);
signal measureHit(curMeasure:int);
signal onBPMChange(newBPM:float);

var paused:bool = false

func _process(elapsed:float) -> void:
	if not paused:
		songPosition += elapsed * 1000

	var oldStep:int = curStep

	var lastChange:Dictionary = {
		"stepTime": 0,
		"songTime": 0,
		"bpm": 0
	}

	for i in bpmChangeMap.size():
		if songPosition >= bpmChangeMap[i].songTime:
			lastChange = bpmChangeMap[i];

	curStep = lastChange["stepTime"] + floor((songPosition - lastChange["songTime"]) / stepCrochet);
	curBeat = floor(curStep / 4);
	curMeasure = floor(curBeat / 4);

	var curScene = get_tree().current_scene

	if oldStep != curStep:
		stepHit.emit(curStep);
		for i in get_tree().current_scene.get_children():
			if i.has_method("stepHit"):
				i.call_deferred("stepHit", curStep)
			if curScene.has_method("stepHit"):
				curScene.call_deferred("stepHit", curStep)

		if curStep % 4 == 0:
			beatHit.emit(curBeat);
			for i in get_tree().current_scene.get_children():
				if i.has_method("beatHit"):
					i.call_deferred("beatHit", curBeat)
			if curScene.has_method("beatHit"):
				curScene.call_deferred("beatHit", curBeat)

		if curBeat % 4 == 0:
			measureHit.emit(curMeasure);
			for i in get_tree().current_scene.get_children():
				if i.has_method("measureHit"):
					i.call_deferred("measureHit", curMeasure)
			if curScene.has_method("measureHit"):
				curScene.call_deferred("measureHit", curMeasure)

func play() -> void: paused = false
func pause() -> void: paused = true
func reset() -> void: songPosition = 0
*/