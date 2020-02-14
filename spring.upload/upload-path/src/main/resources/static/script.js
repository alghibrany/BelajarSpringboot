var controlimg = document.getElementById("filejpg");
        controlimg.addEventListener("change", function (event) {
            // When the control has changed, there are new files
            var files = controlimg.files;


            if (files[0] != null) {
                var img = new Image();
                console.log("Filename: " + files[0].name);
                console.log("Type: " + files[0].type);
                console.log("Size: " + files[0].size + " bytes");
                if (files[0].size > 1000000) {
                    //File too big
                    alert("File too big");
                    document.getElementById("registerButton").disabled = true;
                } else {
                    var fileReader = new FileReader();
                    var type;
                    fileReader.onloadend = function (e) {
                        var arr = (new Uint8Array(e.target.result)).subarray(0, 4);
                        var header = "";
                        for (var i = 0; i < arr.length; i++) {
                            header += arr[i].toString(16);
                        }
                        console.log(header);

                        // Check the file signature against known types
                        switch (header) {
                            case "89504e47":
                            case "ffd8ffe0":
                            case "ffd8ffe1":
                            case "ffd8ffe2":
                            case "ffd8ffe3":
                            case "ffd8ffe8":
                                img.onload = function () {
                                    var sizes = {
                                        width: this.width,
                                        height: this.height
                                    };
                                    URL.revokeObjectURL(this.src);

                                    console.log('onload: sizes', sizes);
                                    console.log('onload: this', this);
                                }

                                var objectURL = URL.createObjectURL(files[0]);

                                console.log('change: file', files[0]);
                                console.log('change: objectURL', objectURL);
                                img.src = objectURL;

                                if (img.sizes.height < 400 || img.sizes.width < 300) {
                                    //Files dimension too small
                                    alert("Dimension too small");
                                    console.log("disabled");
                                    document.getElementById("registerButton").disabled = true; // Or you can use the blob.type as fallback
                                } else {
                                    console.log("enabled");
                                    document.getElementById("registerButton").disabled = false; // Or you can use the blob.type as fallback
                                }
                                break;
                            default:
                                //Files is not jpg/jpeg/png
                                alert("Not accepted image format");
                                console.log("disabled");
                                document.getElementById("registerButton").disabled = true; // Or you can use the blob.type as fallback
                                break;
                        }

                    };
                    fileReader.readAsArrayBuffer(files[0]);

                    console.log(files[0].type);
                }

            } else {
                document.getElementById("registerButton").disabled = false;
            }

        }, false);
