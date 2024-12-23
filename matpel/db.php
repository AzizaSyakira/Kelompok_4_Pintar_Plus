<?php
header("Content-Type: application/json");
include 'db.php';  // Pastikan untuk menghubungkan dengan database pintarplus

if (isset($_GET['action'])) {
    switch ($_GET['action']) {
        // Menambahkan data grade
        case 'addGrade':
            if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                $icon = $_FILES['icon']['tmp_name'];
                if (isset($icon)) {
                    $icon_data = file_get_contents($icon);

                    $sql = "INSERT INTO grade (icon) VALUES (?)";
                    $stmt = $conn->prepare($sql);
                    $stmt->bind_param("s", $icon_data);

                    if ($stmt->execute()) {
                        echo json_encode(["message" => "Grade berhasil ditambahkan."]);
                    } else {
                        echo json_encode(["error" => "Error: " . $conn->error]);
                    }
                } else {
                    echo json_encode(["error" => "File icon tidak ditemukan."]);
                }
            }
            break;

        // Mendapatkan semua data grade
        case 'getGrade':
            if ($_SERVER['REQUEST_METHOD'] == 'GET') {
                $sql = "SELECT * FROM grade";
                $result = $conn->query($sql);
        
                if ($result->num_rows > 0) {
                    $grade_list = [];
                    while ($row = $result->fetch_assoc()) {
                        $row['icon'] = base64_encode($row['icon']);
                        $grade_list[] = $row;
                    }
                    echo json_encode($grade_list);
                } else {
                    echo json_encode(["message" => "Tidak ada grade."]);
                }
            }
            break;

        // Menambahkan data matpel
        case 'addMatpel':
            if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                $grade_id = $_POST['grade_id'];
                $icon = $_FILES['icon']['tmp_name'];

                if (isset($icon)) {
                    $icon_data = file_get_contents($icon);

                    $sql = "INSERT INTO matpel (icon, grade_id) VALUES (?, ?)";
                    $stmt = $conn->prepare($sql);
                    $stmt->bind_param("si", $icon_data, $grade_id);

                    if ($stmt->execute()) {
                        echo json_encode(["message" => "Matpel berhasil ditambahkan."]);
                    } else {
                        echo json_encode(["error" => "Error: " . $conn->error]);
                    }
                } else {
                    echo json_encode(["error" => "File icon tidak ditemukan."]);
                }
            }
            break;

        // Mendapatkan semua data matpel
        case 'getMatpel':
            if ($_SERVER['REQUEST_METHOD'] == 'GET') {
                $sql = "SELECT * FROM matpel";
                $result = $conn->query($sql);
        
                if ($result->num_rows > 0) {
                    $matpel_list = [];
                    while ($row = $result->fetch_assoc()) {
                        $row['icon'] = base64_encode($row['icon']);
                        $matpel_list[] = $row;
                    }
                    echo json_encode($matpel_list);
                } else {
                    echo json_encode(["message" => "Tidak ada matpel."]);
                }
            }
            break;

        // Menambahkan data course
        case 'addCourse':
            if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                $courses = isset($_POST['courses']) ? $_POST['courses'] : '';
                $judul = isset($_POST['judul']) ? $_POST['judul'] : '';
                $link = isset($_POST['link']) ? $_POST['link'] : '';
                $matpel_id = isset($_POST['matpel_id']) ? $_POST['matpel_id'] : '';

                if (empty($courses) || empty($judul) || empty($link) || empty($matpel_id)) {
                    echo json_encode(["error" => "Semua data harus diisi."]);
                    exit;
                }

                // Query untuk memasukkan data course
                $sql = "INSERT INTO courses (courses, judul, link, matpel_id) VALUES (?, ?, ?, ?)";
                $stmt = $conn->prepare($sql);
                $stmt->bind_param("sssi", $courses, $judul, $link, $matpel_id);

                if ($stmt->execute()) {
                    echo json_encode(["message" => "Course berhasil ditambahkan."]);
                } else {
                    echo json_encode(["error" => "Error: " . $conn->error]);
                }
                $stmt->close();
            } else {
                echo json_encode(["error" => "Request method harus POST."]);
            }
            break;

        // Mendapatkan semua data course
        case 'getCourse':
            if ($_SERVER['REQUEST_METHOD'] == 'GET') {
                $sql = "SELECT * FROM courses";
                $result = $conn->query($sql);

                if ($result->num_rows > 0) {
                    $courses_list = [];
                    while ($row = $result->fetch_assoc()) {
                        $courses_list[] = $row;
                    }
                    echo json_encode($courses_list);
                } else {
                    echo json_encode(["message" => "Tidak ada data course."]);
                }
            }
            break;

        default:
            echo json_encode(["error" => "Action tidak valid."]);
            break;
    }
} else {
    echo json_encode(["error" => "Action tidak ditentukan."]);
}

$conn->close();  // Menutup koneksi database
?>